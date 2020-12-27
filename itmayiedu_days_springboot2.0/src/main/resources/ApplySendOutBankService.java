package org.nercita.vcdfms.apply.service;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.nercita.ltxx.core.orm.IBaseDao;
//import org.nercita.ltxx.core.orm.Page;
import org.nercita.ltxx.core.service.BaseService;
import org.nercita.ltxx.core.utils.EncodeUtils;
import org.nercita.vcdfms.apply.dao.ApplyStatusDao;
import org.nercita.vcdfms.apply.dao.BkStatusTrackDao;
import org.nercita.vcdfms.apply.dao.BkTaskDao;
import org.nercita.vcdfms.apply.dao.TBankDataDemandDao;
import org.nercita.vcdfms.apply.dao.TBankDetailDataDao;
import org.nercita.vcdfms.apply.dao.TBankPersonDataDao;
import org.nercita.vcdfms.apply.dao.TBankRelateDao;
import org.nercita.vcdfms.apply.domain.Apply;
import org.nercita.vcdfms.apply.domain.ApplyStatus;
import org.nercita.vcdfms.apply.domain.BkStatusTrack;
import org.nercita.vcdfms.apply.domain.BkTask;
import org.nercita.vcdfms.apply.domain.TBankAccesorry;
import org.nercita.vcdfms.apply.domain.TBankDataDemand;
import org.nercita.vcdfms.apply.domain.TBankDetailData;
import org.nercita.vcdfms.apply.domain.TBankPersonData;
import org.nercita.vcdfms.base.dao.BkContrastDictionaryDao;
import org.nercita.vcdfms.base.dao.YhDao;
import org.nercita.vcdfms.base.domain.BkContrastDictionary;
import org.nercita.vcdfms.base.domain.Yhdm;
import org.nercita.vcdfms.flow.dao.ExamineOptionDao;
import org.nercita.vcdfms.flow.domain.ExamineOption;
import org.nercita.vcdfms.pcosScan.dao.PropertyConfigDao;
import org.nercita.vcdfms.pcosScan.domain.PropertyConfig;
import org.nercita.vcdfms.system.dao.UserPhotoDao;
import org.nercita.vcdfms.system.domain.User;
import org.nercita.vcdfms.system.domain.UserPhoto;
import org.nercita.vcdfms.system.service.UserService;
import org.nercita.vcdfms.util.Commons;
import org.nercita.vcdfms.util.ConfigureUtil;
import org.nercita.vcdfms.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 其他费用表的service
 * 
 * @author zhangwenchao
 * 
 */
@Service
@SuppressWarnings("all")
public class ApplySendOutBankService extends BaseService<Apply, Long> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TBankRelateDao tBankRelateDao;

	@Autowired
	private TBankDataDemandDao tBankDataDemandDao;

	@Autowired
	private TBankPersonDataDao tBankPersonDataDao;

	@Autowired
	private TBankDetailDataDao tBankDetailDataDao;

	@Autowired
	private ApplyStatusDao applyStatusDao;

	@Autowired
	private BkTaskDao taskDao;
	
	@Autowired
	private ApplySendService applySendService;

	// @Autowired
	// private ApplySendBankDao applySendBankDao;

	@Autowired
	private TBankDataDemandService tBankDataDemandService;

	@Autowired
	private TBankAccesorryService tBankAccesorryService;

	@Autowired
	private YhDao yhDao;

	@Autowired
	private BkStatusTrackDao bkStatusTrackDao;
	
	@Autowired
	private BkContrastDictionaryDao bkContrastDictionaryDao;
	

	@Autowired
	private UserPhotoDao userPhotoDao;
	
	@Autowired
	private  ExamineOptionDao examineOptionDao;
	@Autowired
	PropertyConfigDao configDao;
	
	@Autowired
	private PropertyConfigDao propertyConfigDao;
	
	@Override
	protected IBaseDao<Apply, Long> getEntityDao() {
		return null;
	}

	/**
	 * 
	 * @descriptioin TODO //@param @param cxsqid 查询申请id
	 * @param @param pcid 批次ID（即请求单号qqdh）
	 * @param @param projectPath 项目的绝对路径
	 * @param @param userid 当前用户id
	 * @return void
	 */
	public void generateXmlToSend(String qqdh, String projectPath, String userid,String bankCode) {
		try{
		// 读取配置文件的名字
		String uploadPath = projectPath + File.separator + "photo"
				+ File.separator + "uploadFiles";
		TBankDataDemand tdd=new TBankDataDemand();
		tdd=tBankDataDemandService.getDemand(qqdh);
		// String xmlDirpath = ""; // 不同银行编号生成多个文件，在此目录（非文件目录）
		if (qqdh != null && !"".equals(qqdh) && !"null".equals(qqdh)) {
			GenerateXmlByQqdh(uploadPath, qqdh, userid,tdd,bankCode);
		}

		// 1. 读取生成FMQ的配置文件目录，后面需要获取FMQupload上传目录。
		String propertyName="provice";
		String proPath="config/filepath.properties";
		String provice=ConfigureUtil.getResourceByKey("provice",
				proPath);
		//附件目录
		String bankApplyAccesorryPath = PropertiesUtil.bankApplyAccesorryPath;
		// cxsqid = "32700";
		File fileDir = new File(uploadPath + File.separator + qqdh);
		if (fileDir.exists()) {
			File[] files = fileDir.listFiles();//列出这个目录下所有的文件
			if (files.length > 0) {
				for (File file : files) {
					if (file.isDirectory()) {
						if(bankCode.equals(file.getName())){	
							PropertyConfig config=propertyConfigDao.findByProvince(provice, file.getName(), "2");
							String provinceCopy = config.getSendCopyPath();
							String provinceSendBank = config.getSendDirPath();
							String newDir = file.getAbsolutePath();						
							File f1 = new File(newDir);
							File[] fileList = f1.listFiles();
							if (fileList.length > 0) {
								// 需要打成的zip包文件的名字路径
								String zipFilePath = newDir + File.separator+provice
										+ file.getName() + qqdh + ".zip";
								File zipFile = new File(zipFilePath);								
								// 20150717使用zip4j方式压缩文件
								ArrayList filelist = new ArrayList();
								for (int i = 0; i < fileList.length; i++) {
									filelist.add(fileList[i]);
								}
								if("102".equals(String.valueOf(tdd.getSqlxid()))) {
									//	获取附件文件
									File[]  asFileList =  new File(bankApplyAccesorryPath + qqdh).listFiles();
									for(File as : asFileList) {
										filelist.add(as);
									}
								}
								ZipParameters zipParamters = new ZipParameters();
								zipParamters
								.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
								zipParamters
								.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
								zipParamters.setEncryptFiles(true);
								zipParamters
								.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
								zipParamters.setPassword(provice+file.getName()+qqdh);
								
								ZipFile zipFileByPass;
								try {
									zipFileByPass = new ZipFile(zipFilePath);
									zipFileByPass.addFiles(filelist, zipParamters);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								
								if (zipFile.exists()) {
									if (provinceSendBank != null
											&& !"".equals(provinceSendBank)) {
										//如果存在预设时间发送
										if(tdd.getPresetTime() != null){
											provinceSendBank = PropertiesUtil.ZipWaitPath;
										}
										copyFileToDir(zipFile.getAbsolutePath(),
												provinceSendBank);
										// 复制zip包到备份目录
										if (provinceCopy != null && !"".equals(provinceCopy)) {
											String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
											copyFileToDir(zipFile.getAbsolutePath(),provinceCopy+File.separator+file.getName()+File.separator+qqdh);
										}
									}
								}
							}
							
						}
					}
				}
			}
		}

//		// 2. 如果zip文件放到了FMQ上传路径下。审核最后一步，更改申请表状态
		/*tdd.setSqzt("11"); // 已发送
		tBankDataDemandDao.update(tdd);

		// 3. 加入申请状态跟踪记录
		BkStatusTrack bkst = new BkStatusTrack();
		bkst.setQqdh(qqdh);
		bkst.setSqzt("已发送");
		bkst.setCzsj(new Date());
		bkst.setCzrjh(userid);
		bkStatusTrackDao.save(bkst);*/
		//2017年8月17日 17:51:19 注释 bylixiao 转移到ExamineOptionController  949行
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	private String GenerateXmlByQqdh(String uploadPath, String qqdh,
			String userid,TBankDataDemand tdd,String bankCode) {
		// 3. 获得文书表数据（TBankAccesorry）
		List<TBankAccesorry> tbalisttemp = new ArrayList<TBankAccesorry>();
		tbalisttemp = tBankAccesorryService.findByPcid(qqdh, "2");
		String fileDirPath = "";
		Yhdm yhdm = null;
		yhdm = yhDao.getYhmc(bankCode);
		// 账号有数据
		/*if (tbalisttemp != null && tbalisttemp.size() > 0) {
			Yhdm preYhdm = null;
			for (int t = 0; t < tbalisttemp.size(); t++) {
				if(preYhdm != null && yhdm.getYhbh().equals(preYhdm.getYhbh())){
					continue;
				}
				preYhdm = yhdm;
			}
			
		}*/
				// 账户查询查询
				//String flag=yhDao.getContentByYhbh(bankCode);
				String provice = Commons.queryPropertiesFilePath("provice");
				if(1==1){
					if (tdd.getSqlxid() == 101) {
						// 个人涉案对象信息，取得 person表中的数据。
						List<TBankPersonData> grtbpdlist = new ArrayList<TBankPersonData>();
						grtbpdlist = tBankPersonDataDao.findPersonByQqdh(qqdh, bankCode, "个人");
						if(grtbpdlist!=null&&grtbpdlist.size()>0)
						{
							// 生成个人账户信息查询的xml文件。
							fileDirPath = generateXmlForPerson("SS01",grtbpdlist, tdd, uploadPath,bankCode, qqdh);
							// 申请任务记录
							List grzhzzhmlist = new ArrayList();
							for (TBankPersonData grzh : grtbpdlist) {
								String grzhzzhm = grzh.getZjhm()+"!"+grzh.getRwlsh()+"!"+grzh.getCxzl();
								grzhzzhmlist.add(grzhzzhm);
							}
							saveTask(qqdh, grzhzzhmlist, bankCode, userid,"SS01");
							// 2015-09-21
							addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送个人账户信息查询",grtbpdlist.size(), "SS01");
						}
						// 单位涉案对象信息， 取得 person表中的数据。
						List<TBankPersonData> dwtbpdlist = new ArrayList<TBankPersonData>();
						dwtbpdlist = tBankPersonDataDao.findPersonByQqdh(qqdh, bankCode, "单位");
						if(dwtbpdlist!=null&&dwtbpdlist.size()>0)
						{
							// 生成个人账户信息查询的xml文件。
							fileDirPath = generateXmlForPerson("SS02",dwtbpdlist, tdd, uploadPath,bankCode, qqdh);
							// 申请任务记录
							List dwzhzzhmlist = new ArrayList();
							for (TBankPersonData grzh : dwtbpdlist) {
								String grzhzzhm = grzh.getZjhm()+"!"+grzh.getRwlsh()+"!"+grzh.getCxzl();
								dwzhzzhmlist.add(grzhzzhm);
							}
							saveTask(qqdh, dwzhzzhmlist, bankCode, userid,"SS02");
							// 2015-09-21
							addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位账户信息查询",dwtbpdlist.size(), "SS02");
						}
						//个人账户信息
						List<TBankDetailData> grdetaillist = new ArrayList<TBankDetailData>();
						grdetaillist = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'个人','未知'");
						if(grdetaillist!=null&&grdetaillist.size()>0)
						{
							// 生成个人账号明细查询的xml文件。
							fileDirPath = generateXmlForDetail("SS05",grdetaillist, tdd, uploadPath,bankCode, qqdh);
							
							// 申请任务记录
							List grmxzzhmlist = new ArrayList();
							for (TBankDetailData grmx : grdetaillist) {
								String grmxzzhm = grmx.getZh()+"!"+grmx.getRwlsh()+"!"+grmx.getCxnr();
								grmxzzhmlist.add(grmxzzhm);
							}
							saveTask(qqdh, grmxzzhmlist, bankCode, userid,"SS05");
							
							addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送个人开户信息查询",grdetaillist.size(), "SS05");
						}
						
						//单位账户信息
						List<TBankDetailData> dwdetaillist = new ArrayList<TBankDetailData>();
						dwdetaillist = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'单位','未知'");
						if(dwdetaillist!=null&&dwdetaillist.size()>0)
						{
							// 生成单位账号明细查询的xml文件。
							fileDirPath = generateXmlForDetail("SS06",dwdetaillist, tdd, uploadPath,bankCode, qqdh);
							
							// 申请任务记录
							List dwmxzzhmlist = new ArrayList();
							for (TBankDetailData dwmx : dwdetaillist) {
								String dwmxzzhm = dwmx.getZh()+"!"+dwmx.getRwlsh()+"!"+dwmx.getCxnr();
								dwmxzzhmlist.add(dwmxzzhm);
							}
							saveTask(qqdh, dwmxzzhmlist, bankCode, userid,"SS06");
							// 2015-09-21
							
							addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位开户信息查询",dwdetaillist.size(), "SS06");
						}	
					}
					else if(tdd.getSqlxid() == 201 || tdd.getSqlxid() == 202 || tdd.getSqlxid() == 203)
					{
						// 201：冻结；202：续冻；203：解冻
						//个人账户信息
						List<TBankDetailData> grdetaillist = new ArrayList<TBankDetailData>();
						grdetaillist = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'个人','未知'");
						if(grdetaillist!=null&&grdetaillist.size()>0)
						{
							// 生成个人协助冻结查询的xml文件。
							fileDirPath = generateXmlForFreeze("SS17",grdetaillist, tdd, uploadPath,bankCode, qqdh);
							
							//任务管理
							List grxzdjlist = new ArrayList();
							for(TBankDetailData grxzdj:grdetaillist){
								String grxz=grxzdj.getZh()+"!"+grxzdj.getRwlsh()+"!"+grxzdj.getCxnr();
								grxzdjlist.add(grxz);
							}
							saveTask(qqdh, grxzdjlist, bankCode, userid,"SS17");
							
							addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送个人协助冻结查询",grdetaillist.size(), "SS17");
						}
						//单位账户信息
						List<TBankDetailData> danweiDetailList = new ArrayList<TBankDetailData>();
						danweiDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'单位','未知'");
						if(danweiDetailList!=null&&danweiDetailList.size()>0)
						{
							// 生成单位协助冻结查询的xml文件。
							fileDirPath = generateXmlForFreeze("SS18",danweiDetailList, tdd, uploadPath,bankCode, qqdh);
							
							//任务管理
							List dwxzdjlist = new ArrayList();
							for(TBankDetailData dwxzdj:danweiDetailList){
								String dwxz=dwxzdj.getZh()+"!"+dwxzdj.getRwlsh()+"!"+dwxzdj.getCxnr();
								dwxzdjlist.add(dwxz);
							}
							saveTask(qqdh, dwxzdjlist, bankCode, userid,"SS18");
							
							addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位协助冻结查询",danweiDetailList.size(), "SS18");
						}
					} else if (tdd.getSqlxid() == 301 || tdd.getSqlxid() == 302 || tdd.getSqlxid() == 303) {
						// 301：布控（动态）
						//个人账户信息
						List<TBankDetailData> gerenDetailList = new ArrayList<TBankDetailData>();
						gerenDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'个人','未知'");
						if(gerenDetailList!=null&&gerenDetailList.size()>0)
						{
							// 生成个人协助布控查询的xml文件。
							fileDirPath = generateXmlForControl("SS11",	gerenDetailList, tdd, uploadPath,bankCode, qqdh);
							
							//任务管理
							List grxzbkdjlist = new ArrayList();
							for(TBankDetailData grxzbkcx:gerenDetailList){
								String grxzbk=grxzbkcx.getZh()+"!"+grxzbkcx.getRwlsh();
								grxzbkdjlist.add(grxzbk);
							}
							if(grxzbkdjlist != null && grxzbkdjlist.size() > 0){
								saveTask(qqdh, grxzbkdjlist, bankCode, userid,"SS11");
							}
							
							addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送个人协助冻结查询",gerenDetailList.size(), "SS11");
						}
						//单位账户信息
						List<TBankDetailData> dwDetailList = new ArrayList<TBankDetailData>();
						dwDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'单位','未知'");
						if(dwDetailList!=null&&dwDetailList.size()>0)
						{
							// 生成单位协助布控查询的xml文件。
							fileDirPath = generateXmlForControl("SS12",dwDetailList, tdd, uploadPath,bankCode, qqdh);
							
							//任务管理
							List dwxzbkdjlist = new ArrayList();
							for(TBankDetailData dwxzbkcx:dwDetailList){
								String dwxzbk=dwxzbkcx.getZh()+"!"+dwxzbkcx.getRwlsh();
								dwxzbkdjlist.add(dwxzbk);
							}
							saveTask(qqdh, dwxzbkdjlist, bankCode, userid,"SS12");
							
							addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位协助冻结查询",dwDetailList.size(), "SS12");
						}
					} else if (tdd.getSqlxid() == 401 || tdd.getSqlxid() == 402) {
						// 紧急止付
						List<TBankDetailData> grDetailList = new ArrayList<TBankDetailData>();
						grDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'个人','未知'");
						if(grDetailList!=null&&grDetailList.size()>0)
						{
							// 生成账号明细查询的xml文件。
							// 生成个人协助布控查询的xml文件。
							fileDirPath = generateXmlForStopPay("SS21",grDetailList, tdd, uploadPath, bankCode, qqdh);
							
							//任务管理
							List grbkmxdjlist = new ArrayList();
							for(TBankDetailData grbkmxcx:grDetailList){
								String grbkmx=grbkmxcx.getZh()+"!"+grbkmxcx.getRwlsh();
								grbkmxdjlist.add(grbkmx);
							}
							saveTask(qqdh, grbkmxdjlist,  bankCode, userid,"SS21");
							
							addApplyStatus(qqdh, yhdm.getYhmc() ,  bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc()  + "发送个人紧急止付查询",grDetailList.size(), "SS21");
						}
						//单位账户信息
						List<TBankDetailData> dwzfDetailList = new ArrayList<TBankDetailData>();
						dwzfDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'单位','未知'");
						if(dwzfDetailList!=null&&dwzfDetailList.size()>0)
						{
							// 生成单位协助布控查询的xml文件。
							fileDirPath = generateXmlForStopPay("SS22",dwzfDetailList, tdd, uploadPath,bankCode, qqdh);
							
							//任务管理
							List dwbkmxdjlist = new ArrayList();
							for(TBankDetailData dwbkmxcx:dwzfDetailList){
								String dwbkmx=dwbkmxcx.getZh()+"!"+dwbkmxcx.getRwlsh();
								dwbkmxdjlist.add(dwbkmx);
							}
							saveTask(qqdh, dwbkmxdjlist, bankCode, userid,"SS22");
							
							addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位紧急止付查询",dwzfDetailList.size(), "SS22");
						}
					}
//*****************生成协查申请的xml文件*****************
					else if(tdd.getSqlxid() == 102)
					{/*
						//单位账户信息
						List<TBankDetailData> xcDetailList = new ArrayList<TBankDetailData>();
						xcDetailList = tBankDetailDataDao.getXCByQqdh(qqdh, bankCode);
						if(xcDetailList!=null&&xcDetailList.size()>0)
						{
							//TODO 添加附件
							List<TBankPersonData> personList = tBankPersonDataDao.findBySqid(qqdh);
							List<TBankDetailData> detail = tBankDetailDataDao.findByPcid(qqdh);
							//uploadPath
							//bankCode
							tbalisttemp.get(t).setBankcode(yhdm.getProvince());
							//fileDirPath=generateXmlForAssit("AS101", personList, tdd, detail, tbalisttemp, uploadPath, bankCode, qqdh);
							
							//任务管理
							List xcbkmxdjlist = new ArrayList();
							for(TBankDetailData xcbkmxcx:xcDetailList){
								String xcbkmx=xcbkmxcx.getZh()+"!"+xcbkmxcx.getRwlsh();
								xcbkmxdjlist.add(xcbkmx);
							}
							saveTask(qqdh, xcbkmxdjlist, bankCode, userid,"102");
							
							addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送协查申请查询",xcbkmxdjlist.size(), "102");
						}
					*/}
					
					//添加附件
					applySendService.saveJgzAndWs(qqdh, uploadPath ,bankCode);
					List<UserPhoto> photos = null;
					// 查询人警官证
					photos = userPhotoDao.findByUserId(tdd.getCbr1());
					applySendService.saveJgz(photos, uploadPath,bankCode,qqdh,"LI26"+bankCode+qqdh);
					// 协办人警官证
					photos = userPhotoDao.findByUserId(tdd.getCbr2());
					applySendService.saveJgz(photos, uploadPath,bankCode,qqdh,"LI27"+bankCode+qqdh);
				}

		else
		{
			List<TBankDetailData> jjzfAccountlisttemp = tBankDetailDataDao.findByPcid(qqdh);
			Yhdm preYhdm = null;
			Yhdm yhdm1 = null;
			for (int t = 0; t < jjzfAccountlisttemp.size(); t++) {
				//如果是立案决定书则不生成查控包
				if("888888".equals(tbalisttemp.get(t).getBankcode())){
					continue;
				}
				yhdm = yhDao.getYhmc(jjzfAccountlisttemp.get(t).getBankcode());
				if(preYhdm != null && yhdm.getYhbh().equals(preYhdm.getYhbh())){
					continue;
				}
				preYhdm = yhdm;
				if (tdd.getSqlxid() == 401 || tdd.getSqlxid() == 402) {
					// 紧急止付
					List<TBankDetailData> grDetailList = new ArrayList<TBankDetailData>();
					grDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, jjzfAccountlisttemp.get(t).getBankcode(), "'个人','未知'");
					if(grDetailList!=null&&grDetailList.size()>0)
					{
					// 生成账号明细查询的xml文件。
					// 生成个人协助布控查询的xml文件。
					fileDirPath = generateXmlForStopPay("SS21",grDetailList, tdd, uploadPath, jjzfAccountlisttemp.get(t).getBankcode(), qqdh);
						
						//任务管理
						List grbkmxdjlist = new ArrayList();
						for(TBankDetailData grbkmxcx:grDetailList){
							String grbkmx=grbkmxcx.getZh()+"!"+grbkmxcx.getRwlsh();
							grbkmxdjlist.add(grbkmx);
						}
						saveTask(qqdh, grbkmxdjlist,  jjzfAccountlisttemp.get(t).getBankcode(), userid,"SS21");
						
						addApplyStatus(qqdh, yhdm.getYhmc() ,  jjzfAccountlisttemp.get(t).getBankcode(), fileDirPath,"申请发送：向" + yhdm.getYhmc()  + "发送个人紧急止付查询",grDetailList.size(), "SS21");
					}
					//单位账户信息
					List<TBankDetailData> dwzfDetailList = new ArrayList<TBankDetailData>();
					dwzfDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, jjzfAccountlisttemp.get(t).getBankcode(), "'单位','未知'");
					if(dwzfDetailList!=null&&dwzfDetailList.size()>0)
					{
					// 生成单位协助布控查询的xml文件。
					fileDirPath = generateXmlForStopPay("SS22",dwzfDetailList, tdd, uploadPath,jjzfAccountlisttemp.get(t).getBankcode(), qqdh);
						
						//任务管理
						List dwbkmxdjlist = new ArrayList();
						for(TBankDetailData dwbkmxcx:dwzfDetailList){
							String dwbkmx=dwbkmxcx.getZh()+"!"+dwbkmxcx.getRwlsh();
							dwbkmxdjlist.add(dwbkmx);
						}
						saveTask(qqdh, dwbkmxdjlist, jjzfAccountlisttemp.get(t).getBankcode(), userid,"SS22");
						
						addApplyStatus(qqdh, yhdm.getYhmc(), jjzfAccountlisttemp.get(t).getBankcode(), fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位紧急止付查询",dwzfDetailList.size(), "SS22");
					}
				}
				//添加附件
				//applySendService.saveJgzAndWs(qqdh, uploadPath ,bankCode);
			}
		}
		return "";
	}

	/**
	 * 保存任务管理
	 * 
	 * @param qqdh
	 * @param bankCode
	 * @param zzhm
	 * @param fksj
	 * @param cbr1
	 */
	// @Transactional
	private void saveTask(String qqdh, List<String> list, String bankCode,
			String userid,String sqlx) {

		List<BkTask> list_bt = new ArrayList<BkTask>();
		Date d = new Date();
		BkTask bt = null;
		for (int i = 0; i < list.size(); i++) {
			bt = new BkTask();
			String zzhmAndRwlsh = list.get(i);
			String[] temp = zzhmAndRwlsh.split("!");
			
			if(temp != null){
				if(temp.length>2){
					bt.setZzhm(temp[0]);
					bt.setRwlsh(temp[1]);
					bt.setCxzl(temp[2]);
				}else{
					bt.setZzhm(temp[0]);
					bt.setRwlsh(temp[1]);
				}
			}
			
			bt.setQqdh(qqdh);
			bt.setBankcode(bankCode);
			// bt.setFksj(new Date());
			bt.setFssj(d);
			bt.setCbr1(userid);
			bt.setSqlx(sqlx);
			bt.setYdflag("0");
			list_bt.add(bt);
		}
		if (list_bt != null && list_bt.size() > 0) {
			taskDao.save(list_bt);
		}
	}


	/**
	 * 
	 * @descriptioin TODO
	 * @param @param fileDirPath 文件路径
	 * @param @param FMQUploadDir 上传到FMQ设置的上传路径
	 * @return void
	 */
	private void copyFileToDir(String fileDirPath, String FMQUploadDir) {

		// 测试地址
		// FMQUploadDir = "D:\\uploadFiles";

		if (!"".equals(fileDirPath) && fileDirPath != null) {
			File file1 = new File(fileDirPath);
			String fmqUploadFile = "";
			if (file1.exists()) {
				fmqUploadFile = file1.getName();
			}

			if (!"".equals(fmqUploadFile)) {

				File fmqUploadDir = new File(FMQUploadDir);

				if (!fmqUploadDir.exists()) {
					//System.out.println("不存在目录，则创建");
					fmqUploadDir.mkdirs();
				}

				File fmqFile = new File(FMQUploadDir + File.separator
						+ fmqUploadFile);

				BufferedInputStream inbuff = null;
				BufferedOutputStream outbuff = null;

				try {
					inbuff = new BufferedInputStream(new FileInputStream(
							fileDirPath));
					outbuff = new BufferedOutputStream(new FileOutputStream(
							fmqFile));

					byte[] b = new byte[1024];
					int len;
					while ((len = inbuff.read(b)) != -1) {
						outbuff.write(b, 0, len);
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (inbuff != null)
							inbuff.close();
						if (outbuff != null)
							outbuff.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	/**
	 * 
	 * @descriptioin TODO
	 * @param @param pcid 即QQDH
	 * @param @param yhmc
	 * @param @param bankcode
	 * @param @param fileDirPath
	 * @param @param ztsm
	 * @param @param applyCount
	 * @param @param applyInfo
	 * @return void
	 */
	@Transactional
	// private void addApplyStatus(String cxsqid,String yhmc,String
	// bankcode,String fileDirPath,String ztsm,int grzhslCount,int
	// dwzhslCount,int grkhslCount,int dwkhslCount){
	private void addApplyStatus(String pcid, String yhmc, String bankcode,
			String fileDirPath, String ztsm, int applyCount, String applyInfo) {
		// 对不同银行操作后，更改申请状态
		ApplyStatus as = new ApplyStatus();

		// Long a = 0l;
		// try {
		// a = Long.parseLong(cxsqid);
		// } catch (NumberFormatException e) {
		// e.printStackTrace();
		// }
		// as.setCxsqid(a);
		as.setQqdh(pcid);
		as.setShzt("3");
		as.setCreattime(new Date());
		as.setYhmc(yhmc);
		as.setYhdm(bankcode);
		as.setZtsm(ztsm);
		as.setFswjdz(fileDirPath);
		as.setFssj(new Date());
		as.setSqsl(applyCount);
		as.setSqlx(applyInfo);

		applyStatusDao.save(as);
	}

	private Object isNullEmpty(Object o) {
		if (o == null)
			return "";
		else
			return o;
	}

	/**
	 * 
	 * @descriptioin TODO
	 * @param @param file 配置文件的路径
	 * @param @param section 要获取的变量所在的段名称
	 * @param @param variable 要获得的变量名称
	 * @param @param defaultValue 变量名称不存在时的默认值
	 * @param @return
	 * @param @throws IOException
	 * @return String
	 */
	public static String getProfileString(String file, String section,
			String variable, String defaultValue) throws Exception {

		String strLine, value = "";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		boolean isInSection = false;

		try {
			while ((strLine = reader.readLine()) != null) {
				strLine = strLine.trim();
				strLine = strLine.split("[;]")[0];

				// System.out.println("strLine:"+strLine);
				Pattern p;
				Matcher m;

				p = Pattern.compile("\\[\\s*.*\\s*\\]");
				// p = Pattern.compile("\\["+section+"\\]");

				m = p.matcher((strLine));
				if (m.matches()) {
					p = Pattern.compile("\\[\\s*" + section + "\\s*\\]");
					// p=Pattern.compile("\\[" + section + "\\]");
					m = p.matcher(strLine);
					if (m.matches()) {
						isInSection = true;
					} else {
						isInSection = false;
					}
				}
				if (isInSection == true) {
					strLine = strLine.trim();
					String[] strArray = strLine.split("=");
					if (strArray.length == 1) {
						value = strArray[0].trim();
						if (value.equalsIgnoreCase(variable)) {
							value = "";
							return value;
						}
					} else if (strArray.length == 2) {
						value = strArray[0].trim();
						if (value.equalsIgnoreCase(variable)) {
							value = strArray[1].trim();
							return value;
						}
					} else if (strArray.length > 2) {
						value = strArray[0].trim();
						if (value.equalsIgnoreCase(variable)) {
							value = strLine.substring(strLine.indexOf("=") + 1)
									.trim();
							return value;
						}
					}
				}
			}
			defaultValue = value;
			System.out.print("发送目录========" + value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}

		return defaultValue;
	}

	
	public String toZjlxStr(String bankCode){
		String zjlxStr = "";
		if("01020000".equals(bankCode)||"C001B101650104101".equals(bankCode)){
			zjlxStr = "9";
		}else{
			zjlxStr ="110031";
		}
		return zjlxStr;
	}
	
	
	/**
	 * 身份证查询（账户查询）
	 * 
	 * @descriptioin TODO
	 * @param
	 * @return void
	 */
	public String generateXmlForPerson(String grOrdwZHXXCXSQ,
			List<TBankPersonData> personList, TBankDataDemand tbdd,
			String uploadPath, String bankCode, String qqdh) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
		// String currentTime = sdf1.format(new Date());
		// String currentDate = sdf2.format(new Date());
		String fasongTime = sdf3.format(new Date());

		Document outDocument = DocumentHelper.createDocument();
		outDocument.setXMLEncoding("utf-8");
		Element outRoot = DocumentHelper.createElement("QUERYFORM");
		outDocument.setRootElement(outRoot);

		Element BasicInfoElement = outRoot.addElement("BASICINFO");
		Element QueryPersonElement = outRoot.addElement("QUERYPERSON");

		if (tbdd != null) {

			BasicInfoElement.addAttribute("QQDBS", transToBase64(isNullToEmpty(qqdh))); // 请求单标识 qqdh
			BasicInfoElement.addAttribute("QQCSLX", transToBase64(isNullToEmpty(getCslx(tbdd.getSqlxid())))); // 请求措施类型
			
			String sqjgdmStr = tbdd.getDeptId();
			if(sqjgdmStr!=null && sqjgdmStr.length()>2){
				sqjgdmStr = sqjgdmStr.substring(0, 2);
				sqjgdmStr = sqjgdmStr+"0000";
			}else{
				sqjgdmStr="";
			}
			
			BasicInfoElement.addAttribute("SQJGDM", transToBase64(isNullToEmpty(sqjgdmStr))); // 申请机构代码
			BasicInfoElement.addAttribute("MBJGDM", transToBase64(isNullToEmpty(bankCode))); // 目标机构代码
			if ("SS01".equals(grOrdwZHXXCXSQ)) { // 个人
				BasicInfoElement.addAttribute("CKZTLB", transToBase64("01")); //主体类别
			} else if ("SS02".equals(grOrdwZHXXCXSQ)) { // 单位
				BasicInfoElement.addAttribute("CKZTLB", transToBase64("02"));
			}
			
			BasicInfoElement.addAttribute("JJCD", transToBase64(isNullToEmpty(tbdd.getJjcdItemCode()))); // 紧急程度 jjcd
			BasicInfoElement.addAttribute("AJLX", transToBase64(isNullToEmpty(tbdd.getAjlx_name()))); // 案件类型
			BasicInfoElement.addAttribute("BEIZ", transToBase64("")); // 备注
																		// remark
			BasicInfoElement.addAttribute("FSSJ", transToBase64(isNullToEmpty(fasongTime))); // 发送时间

			

			QueryPersonElement.addAttribute("QQRXM", transToBase64(isNullToEmpty(tbdd.getLxrxm1()))); // 请求人姓名
																		// lxrxm1
			QueryPersonElement.addAttribute("QQRZJLX", transToBase64(toZjlxStr(bankCode))); // 请求人证件类型 -
																	// 110031
			QueryPersonElement.addAttribute("QQRZJHM", transToBase64(isNullToEmpty(tbdd.getCbr1()))); // 请求人证件号码（请求人警号
																		// cbr1）
			QueryPersonElement.addAttribute("QQRDWMC", transToBase64(isNullToEmpty(tbdd.getCbdw()))); // 请求人单位名称
																		// cbdw
			QueryPersonElement.addAttribute("QQRSJH", transToBase64(isNullToEmpty(tbdd.getCbr1sjh()))); // 请求人手机号
																			// cbr1sjh
			// QueryPersonElement.addAttribute("QQRJH", tbdd.getCbr1()); //
			// 请求人警号 cbr1

			User user = userService.findUniqueByProperty("userid", tbdd.getCbr2());
			String xcrxm = user.getOperatorname();
			
			QueryPersonElement.addAttribute("XCRXM", transToBase64(isNullToEmpty(xcrxm))); // 协查人姓名
																		// lxrxm2
			QueryPersonElement.addAttribute("XCRZJLX", transToBase64(toZjlxStr(bankCode))); // 协查人证件类型 -
																	// 110031
			QueryPersonElement.addAttribute("XCRZJHM", transToBase64(isNullToEmpty(tbdd.getCbr2()))); // 协查人证件号码（协查人警号
																		// cbr2）
			//QueryPersonElement.addAttribute("XCRSJH", tbdd.getCbr2sjh()); // 协查人手机号
																			// cbr2sjh
			// QueryPersonElement.addAttribute("XCRJH", tbdd.getCbr2()); //
			// 协查人警号 cbr2

		}

		Element QueryMainsElement = outRoot.addElement("QUERYMAINS");

		if (personList != null && personList.size() > 0) {
			for (int i = 0; i < personList.size(); i++) {
				TBankPersonData tbPersonData = personList.get(i);

				Element QueryMainElement = QueryMainsElement
						.addElement("QUERYMAIN");
				QueryMainElement.addAttribute("RWLSH", transToBase64(isNullToEmpty(tbPersonData.getRwlsh()))); // 任务流水号
				String standardCode = tbPersonData.getZjlxCode();
				String actualCode = "";
				if(standardCode!=null && !"".equals(standardCode)){
					BkContrastDictionary bkContrastDictionary = bkContrastDictionaryDao.getBkContrastDictionary(bankCode, standardCode);
					if(bkContrastDictionary!=null)
					{
						actualCode = bkContrastDictionary.getActualCode();
					}
					else
					{
						actualCode=tbPersonData.getZjlxCode();
					}
				}
				QueryMainElement.addAttribute("ZZLX", transToBase64(isNullToEmpty(actualCode))); // 证照类型代码
				
				QueryMainElement.addAttribute("ZZHM", transToBase64(isNullToEmpty(tbPersonData.getZjhm()))); // 证照号码
				QueryMainElement.addAttribute("ZTMC", transToBase64(isNullToEmpty(tbPersonData.getXm()))); // 主体名称
				QueryMainElement.addAttribute("CXNR", transToBase64(isNullToEmpty(tbPersonData.getCxzl()))); // 查询种类
				
				if(tbPersonData.getCxzl()!=null && !"".equals(tbPersonData.getCxzl()) && tbPersonData.getCxzl().equals("01"))
				{
					QueryMainElement.addAttribute("MXSDLX", "");
					QueryMainElement.addAttribute("MXQSSJ", "");
					QueryMainElement.addAttribute("MXJZSJ", "");
				}
				else
				{
					if(tbPersonData.getSjbs()!=null)
					{
						//suwen 2016年2月18日17:59:58 如果选择的时间类型为02一年以内，则将时间类型置换为03自定义时间段
						if("02".equals(tbPersonData.getSjbs())){
							QueryMainElement.addAttribute("MXSDLX", transToBase64("03")); // 明细时段类型
						}else{
							QueryMainElement.addAttribute("MXSDLX", transToBase64(tbPersonData.getSjbs())); // 明细时段类型
						}
					}
					else
					{
						QueryMainElement.addAttribute("MXSDLX", "");
					}
					// 明细起始时间
					if (tbPersonData.getKssj() != null) {
						String qssj = sdf2.format(tbPersonData.getKssj());
						QueryMainElement.addAttribute("MXQSSJ", transToBase64(isNullToEmpty(qssj)));
					} else {
						QueryMainElement.addAttribute("MXQSSJ", "");
					}
					// 明细截至时间
					if (tbPersonData.getJssj() != null) {
						String jzsj = sdf2.format(tbPersonData.getJssj());
						QueryMainElement.addAttribute("MXJZSJ", transToBase64(isNullToEmpty(jzsj)));
					} else {
						QueryMainElement.addAttribute("MXJZSJ", "");
					}
				}
			}
		}

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");

		// 上传文件的文件路径
		String outFilePath = "";

		// 将xml文件上传
		FileOutputStream fileout = null;
		XMLWriter outXmlWriter = null;
		String outXmlFile = ""; // 输出的xml文件名

		try {
			// 输出到控制台
			// XMLWriter xmlWriter = new XMLWriter();
			// xmlWriter.write(outdocument);

			// outXmlFile = grOrdwZHXXCXSQ+currentDate+bankCode+qqdh+".xml";
			outXmlFile = grOrdwZHXXCXSQ + bankCode + qqdh + ".xml";

			String outDirPath = "";
			outDirPath = uploadPath + File.separator + qqdh + File.separator
					+ bankCode; // 带有银行代码的目录路径;
			File outDir = new File(outDirPath);

			if (!outDir.exists()) {
				//System.out.println("不存在目录，则创建");
				outDir.mkdirs();
			}

			outFilePath = outDirPath + File.separator + outXmlFile;

			// 输出XML文件
			fileout = new FileOutputStream(outFilePath);
			outXmlWriter = new XMLWriter(fileout, format);
			outXmlWriter.write(outDocument);

			fileout.flush();
			outXmlWriter.flush();

			fileout.close();
			outXmlWriter.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileout != null)
					fileout.close();
				if (outXmlWriter != null)
					outXmlWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return outFilePath;
	}

	/**
	 * 账户资料查询（账号查询）
	 * 
	 * @descriptioin TODO
	 * @param
	 * @return void
	 */
	public String generateXmlForDetail(String grOrdwKHXXCXSQ,
			List<TBankDetailData> detailList, TBankDataDemand tbdd,
			String uploadPath, String bankCode, String qqdh) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
		// String currentTime = sdf1.format(new Date());
		// String currentDate = sdf2.format(new Date());
		String fasongTime = sdf3.format(new Date());

		Document outDocument = DocumentHelper.createDocument();
		outDocument.setXMLEncoding("utf-8");
		Element outRoot = DocumentHelper.createElement("QUERYFORM");
		outDocument.setRootElement(outRoot);

		Element BasicInfoElement = outRoot.addElement("BASICINFO");
		Element QueryPersonElement = outRoot.addElement("QUERYPERSON");

		if (tbdd != null) {

			BasicInfoElement.addAttribute("QQDBS", transToBase64(isNullToEmpty(qqdh))); // 请求单标识 qqdh
			BasicInfoElement.addAttribute("QQCSLX", transToBase64(isNullToEmpty(getCslx(tbdd.getSqlxid())))); // 请求措施类型
			
			String sqjgdmStr = tbdd.getDeptId();
			if(sqjgdmStr!=null && sqjgdmStr.length()>2){
				sqjgdmStr = sqjgdmStr.substring(0, 2);
				sqjgdmStr = sqjgdmStr+"0000";
			}else{
				sqjgdmStr="";
			}
			BasicInfoElement.addAttribute("SQJGDM", transToBase64(isNullToEmpty(sqjgdmStr))); // 申请机构代码
			
			BasicInfoElement.addAttribute("MBJGDM", transToBase64(isNullToEmpty(bankCode))); // 目标机构代码
			if ("SS05".equals(grOrdwKHXXCXSQ)) { // 个人
				BasicInfoElement.addAttribute("CKZTLB", transToBase64("01")); // 主体类别
			} else if ("SS06".equals(grOrdwKHXXCXSQ)) { // 单位
				BasicInfoElement.addAttribute("CKZTLB", transToBase64("02"));
			}
			BasicInfoElement.addAttribute("JJCD", transToBase64(isNullToEmpty(String.valueOf(tbdd.getJjcdItemCode())))); // 紧急程度 jjcd
			BasicInfoElement.addAttribute("AJLX", transToBase64(isNullToEmpty(tbdd.getAjlx_name()))); // 案件类型
			BasicInfoElement.addAttribute("BEIZ", transToBase64("")); // 备注
																		// remark
			BasicInfoElement.addAttribute("FSSJ", transToBase64(isNullToEmpty(fasongTime))); // 发送时间

			

			QueryPersonElement.addAttribute("QQRXM", transToBase64(isNullToEmpty(tbdd.getLxrxm1()))); // 请求人姓名
																		// lxrxm1
//			QueryPersonElement.addAttribute("QQRZJLX", EncodeUtils.base64Encode("110031".getBytes())); // 请求人证件类型 -
			QueryPersonElement.addAttribute("QQRZJLX", transToBase64(toZjlxStr(bankCode))); // 请求人证件类型 -
																	// 110031
			QueryPersonElement.addAttribute("QQRZJHM", transToBase64(isNullToEmpty(tbdd.getCbr1()))); // 请求人证件号码（请求人警号
																		// cbr1）
			QueryPersonElement.addAttribute("QQRDWMC", transToBase64(isNullToEmpty(tbdd.getCbdw()))); // 请求人单位名称
																		// cbdw
			QueryPersonElement.addAttribute("QQRSJH", transToBase64(isNullToEmpty(tbdd.getCbr1sjh()))); // 请求人手机号
																			// cbr1sjh
			// QueryPersonElement.addAttribute("QQRJH", tbdd.getCbr1()); //
			// 请求人警号 cbr1

			User user = userService.findUniqueByProperty("userid", tbdd.getCbr2());
			String xcrxm = user.getOperatorname();
			
			QueryPersonElement.addAttribute("XCRXM", transToBase64(isNullToEmpty(xcrxm))); // 协查人姓名
																		// lxrxm2
			QueryPersonElement.addAttribute("XCRZJLX", transToBase64(toZjlxStr(bankCode))); // 协查人证件类型 -
																	// 110031
			QueryPersonElement.addAttribute("XCRZJHM", transToBase64(isNullToEmpty(tbdd.getCbr2()))); // 协查人证件号码（协查人警号
																		// cbr2）
			//QueryPersonElement.addAttribute("XCRSJH", tbdd.getCbr2sjh()); // 协查人手机号
																			// cbr2sjh
			// QueryPersonElement.addAttribute("XCRJH", tbdd.getCbr2()); //
			// 协查人警号 cbr2

		}

		Element QueryAccountsElement = outRoot.addElement("QUERYACCOUNTS");

		if (detailList != null && detailList.size() > 0) {
			for (int i = 0; i < detailList.size(); i++) {
				TBankDetailData tbDetailData = detailList.get(i);

				Element QueryAccountElement = QueryAccountsElement
						.addElement("QUERYACCOUNT");
				QueryAccountElement.addAttribute("RWLSH", transToBase64(isNullToEmpty(tbDetailData.getRwlsh()))); // 任务流水号
				QueryAccountElement.addAttribute("CXZH", transToBase64(isNullToEmpty(tbDetailData.getZh()))); // 查询账卡号
				QueryAccountElement.addAttribute("CXNR", transToBase64(isNullToEmpty(tbDetailData.getCxnr()))); // 查询内容
				
				// 时间端类型
				if (tbDetailData.getSjbs()!= null) {
					QueryAccountElement.addAttribute("MXSDLX", transToBase64(tbDetailData.getSjbs()));
				} else {
					QueryAccountElement.addAttribute("MXSDLX", "");
				}
				if(tbDetailData.getCxnr()!=null && !"".equals(tbDetailData.getCxnr()) && tbDetailData.getCxnr().equals("01"))
				{
					QueryAccountElement.addAttribute("MXSDLX", "");
					QueryAccountElement.addAttribute("MXQSSJ", "");
					QueryAccountElement.addAttribute("MXJZSJ", "");
				}
				else {
					// 时间端类型
					if (tbDetailData.getSjbs()!= null) {
						//suwen 2016年2月18日17:59:58 如果选择的时间类型为02一年以内，则将时间类型置换为03自定义时间段
						if("02".equals(tbDetailData.getSjbs())){
							QueryAccountElement.addAttribute("MXSDLX", transToBase64("03")); // 明细时段类型
						}else{
							QueryAccountElement.addAttribute("MXSDLX", transToBase64(tbDetailData.getSjbs())); // 明细时段类型
						}
					} else {
						QueryAccountElement.addAttribute("MXSDLX", "");
					}
					// 明细起始时间
					if (tbDetailData.getKssj() != null) {
						String qssj = sdf2.format(tbDetailData.getKssj());
						QueryAccountElement.addAttribute("MXQSSJ", transToBase64(isNullToEmpty(qssj)));
					} else {
						QueryAccountElement.addAttribute("MXQSSJ", "");
					}
					// 明细截至时间
					if (tbDetailData.getJssj() != null) {
						String jzsj = sdf2.format(tbDetailData.getJssj());
						QueryAccountElement.addAttribute("MXJZSJ", transToBase64(isNullToEmpty(jzsj)));
					} else {
						QueryAccountElement.addAttribute("MXJZSJ", "");
					}
				}
			}
		}

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");

		// 上传文件的文件路径
		String outFilePath = "";

		// 将xml文件上传
		FileOutputStream fileout = null;
		XMLWriter outXmlWriter = null;
		String outXmlFile = ""; // 输出的xml文件名

		// Map map = new HashMap();

		// String ymdStr = DateUtil.formatToString(new Date()); //年月日格式

		try {
			// 输出到控制台
			// XMLWriter xmlWriter = new XMLWriter();
			// xmlWriter.write(outdocument);

			// outXmlFile = grOrdwKHXXCXSQ+currentDate+bankCode+qqdh+".xml";
			outXmlFile = grOrdwKHXXCXSQ + bankCode + qqdh + ".xml";
			// String outDirPath = "E:\\test";
			String outDirPath = "";
			outDirPath = uploadPath + File.separator + qqdh + File.separator
					+ bankCode; // 带有银行代码的目录路径;
			File outDir = new File(outDirPath);

			if (!outDir.exists()) {
				//System.out.println("不存在目录，则创建");
				outDir.mkdirs();
			}

			outFilePath = outDirPath + File.separator + outXmlFile;

			// 文件目录路径
			// map.put("outDirPath", outDirPath);
			// xml文件路径
			// map.put("outXmlFilePath", outFilePath);

			// 输出XML文件
			fileout = new FileOutputStream(outFilePath);
			outXmlWriter = new XMLWriter(fileout, format);
			outXmlWriter.write(outDocument);

			fileout.flush();
			outXmlWriter.flush();

			fileout.close();
			outXmlWriter.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileout != null)
					fileout.close();
				if (outXmlWriter != null)
					outXmlWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return outFilePath;
	}

	/**
	 * 协助冻结查询（账号查询、detail查询）
	 * 
	 * @descriptioin TODO
	 * @param
	 * @return void
	 */
	public String generateXmlForFreeze(String grOrdwXZDJCXSQ,
			List<TBankDetailData> detailList, TBankDataDemand tbdd,
			String uploadPath, String bankCode, String qqdh) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
		// String currentTime = sdf1.format(new Date());
		// String currentDate = sdf2.format(new Date());
		String fasongTime = sdf3.format(new Date());

		Document outDocument = DocumentHelper.createDocument();
		outDocument.setXMLEncoding("utf-8");
		Element outRoot = DocumentHelper.createElement("FREEZEFORM");
		outDocument.setRootElement(outRoot);
		Element BasicInfoElement = outRoot.addElement("BASICINFO");
		Element QueryPersonElement = outRoot.addElement("QUERYPERSON");

		if (tbdd != null) {

			BasicInfoElement.addAttribute("QQDBS", transToBase64(isNullToEmpty(qqdh))); // 请求单标识 qqdh
			BasicInfoElement.addAttribute("QQCSLX", transToBase64(isNullToEmpty(getCslx(tbdd.getSqlxid())))); // 请求措施类型

			String sqjgdmStr = tbdd.getDeptId();
			if(sqjgdmStr!=null && sqjgdmStr.length()>2){
				sqjgdmStr = sqjgdmStr.substring(0, 2);
				sqjgdmStr = sqjgdmStr+"0000";
			}else{
				sqjgdmStr="";
			}
			BasicInfoElement.addAttribute("SQJGDM", transToBase64(isNullToEmpty(sqjgdmStr))); // 申请机构代码

			BasicInfoElement.addAttribute("MBJGDM", transToBase64(isNullToEmpty(bankCode))); // 目标机构代码
			if ("SS17".equals(grOrdwXZDJCXSQ)) { // 个人
				BasicInfoElement.addAttribute("CKZTLB", transToBase64("01")); // 主体类别
			} else if ("SS18".equals(grOrdwXZDJCXSQ)) { // 单位
				BasicInfoElement.addAttribute("CKZTLB", transToBase64("02"));
			}
			BasicInfoElement.addAttribute("JJCD", transToBase64(isNullToEmpty(String.valueOf(tbdd.getJjcdItemCode())))); // 紧急程度 jjcd
			BasicInfoElement.addAttribute("AJLX", transToBase64(isNullToEmpty(tbdd.getAjlx_name()))); // 案件类型
			BasicInfoElement.addAttribute("BEIZ", transToBase64("")); // 备注
																		// remark
			BasicInfoElement.addAttribute("FSSJ", transToBase64(isNullToEmpty(fasongTime))); // 发送时间
			

			QueryPersonElement.addAttribute("QQRXM", transToBase64(isNullToEmpty(tbdd.getLxrxm1()))); // 请求人姓名
																		// lxrxm1
//			QueryPersonElement.addAttribute("QQRZJLX", EncodeUtils.base64Encode("110031".getBytes())); // 请求人证件类型 -
			QueryPersonElement.addAttribute("QQRZJLX", transToBase64(toZjlxStr(bankCode))); // 请求人证件类型 -
																	// 110031
			QueryPersonElement.addAttribute("QQRZJHM", transToBase64(isNullToEmpty(tbdd.getCbr1()))); // 请求人证件号码（请求人警号
																		// cbr1）
//			QueryPersonElement.addAttribute("QQRDWMC", EncodeUtils.base64Encode(isNullToEmpty(tbdd.getCbdw()).getBytes())); // 请求人单位名称
			QueryPersonElement.addAttribute("QQRDWMC", Base64.encode(isNullToEmpty(tbdd.getCbdw()), "utf-8")); // 请求人单位名称
			//System.out.println("========="+Base64.encode(isNullToEmpty(tbdd.getCbdw()), "utf-8"));
																		// cbdw
			QueryPersonElement.addAttribute("QQRSJH", transToBase64(isNullToEmpty(tbdd.getCbr1sjh()))); // 请求人手机号
																			// cbr1sjh
			// QueryPersonElement.addAttribute("QQRJH", tbdd.getCbr1()); //
			// 请求人警号 cbr1

			User user = userService.findUniqueByProperty("userid", tbdd.getCbr2());
			String xcrxm = user.getOperatorname();
			
			QueryPersonElement.addAttribute("XCRXM", transToBase64(isNullToEmpty(xcrxm))); // 协查人姓名
			
//			QueryPersonElement.addAttribute("XCRXM", EncodeUtils.base64Encode(isNullToEmpty(tbdd.getLxrxm2()).getBytes())); // 协查人姓名
																		// lxrxm2
//			QueryPersonElement.addAttribute("XCRZJLX", EncodeUtils.base64Encode("110031".getBytes())); // 协查人证件类型 -
			QueryPersonElement.addAttribute("XCRZJLX", transToBase64(toZjlxStr(bankCode))); // 协查人证件类型 -
																	// 110031
			QueryPersonElement.addAttribute("XCRZJHM", transToBase64(isNullToEmpty(tbdd.getCbr2()))); // 协查人证件号码（协查人警号
																		// cbr2）
			//QueryPersonElement.addAttribute("XCRSJH", tbdd.getCbr2sjh()); // 协查人手机号
																			// cbr2sjh
			// QueryPersonElement.addAttribute("XCRJH", tbdd.getCbr2()); //
			// 协查人警号 cbr2

		}

		Element FreezeAccountsElement = outRoot.addElement("FREEZEACCOUNTS");

		if (detailList != null && detailList.size() > 0) {
			for (int i = 0; i < detailList.size(); i++) {
				TBankDetailData tbDetailData = detailList.get(i);

				Element FreezeAccountElement = FreezeAccountsElement
						.addElement("FREEZEACCOUNT");
				
				FreezeAccountElement.addAttribute("RWLSH", transToBase64(isNullToEmpty(tbDetailData.getRwlsh()))); // 任务流水号
				FreezeAccountElement.addAttribute("YRWLSH", transToBase64(isNullToEmpty(tbDetailData.getYrwlsh()))); // 原任务流水号
				
//				FreezeAccountElement.addAttribute("YQQDBS",	EncodeUtils.base64Encode(isNullToEmpty(tbDetailData.getYqqdh()).getBytes())); // 原请求单号
				FreezeAccountElement.addAttribute("DJZHHZ",	transToBase64(isNullToEmpty(tbDetailData.getDjzhhz()))); // 冻结账户户主
				//FreezeAccountElement.addAttribute("ZZLXDM",	EncodeUtils.base64Encode(isNullToEmpty(tbDetailData.getZjlxCode()).getBytes())); // 证件类型代码
				
				String standardCode = tbDetailData.getZjlxCode();
				String actualCode = "";
				if(standardCode!=null && !"".equals(standardCode)){
					BkContrastDictionary bkContrastDictionary = bkContrastDictionaryDao.getBkContrastDictionary(bankCode, standardCode);
					if(bkContrastDictionary!=null)
					{
						actualCode = bkContrastDictionary.getActualCode();
					}
					else
					{
						actualCode=tbDetailData.getZjlxCode();
					}
				}
				FreezeAccountElement.addAttribute("ZZLXDM", transToBase64(isNullToEmpty(actualCode))); // 证照类型代码

				FreezeAccountElement.addAttribute("ZZHM", transToBase64(isNullToEmpty(tbDetailData.getZjhm()))); // 证件号码
				FreezeAccountElement.addAttribute("ZH", transToBase64(isNullToEmpty(tbDetailData.getZh()))); // 冻结账号
				FreezeAccountElement.addAttribute("ZHXH", transToBase64(isNullToEmpty(tbDetailData.getZhzzh()))); // 冻结账户序号
				
				FreezeAccountElement.addAttribute("DJFS", transToBase64(isNullToEmpty(tbDetailData.getCxnr()))); // 冻结方式
				if(tbDetailData.getJe()!=null)
				{
					if(tbDetailData.getCxnr().equals("02"))
					{
						FreezeAccountElement.addAttribute("JE","");	
					}
					else
					{
						FreezeAccountElement.addAttribute("JE",	transToBase64(isNullToEmpty(Commons.getJeStr(tbDetailData.getJe())))); // 金额
					}
				}
				else
				{
					FreezeAccountElement.addAttribute("JE","");	
				}
				FreezeAccountElement.addAttribute("BZ",	transToBase64(isNullToEmpty(tbDetailData.getBzCode()))); // 币种

				// 开始时间（发送时间）
				String kssjstr = sdf2.format(new Date());
				//add by suwen 2017年8月23日 18:10:27  冻结添加预设时间冻结
				if(tbDetailData.getKssj() != null){
					kssjstr = sdf2.format(tbDetailData.getKssj());
				}
				tbDetailData.setKssj(new Date());
				if (tbDetailData.getQqcslx().equals("06") || tbDetailData.getQqcslx().equals("14")) {
					TBankDetailData ytbDetailData = tBankDetailDataDao.getDetailByPcid(tbDetailData.getYqqdh(),tbDetailData.getZh());

					if (ytbDetailData != null) {
						try {
							kssjstr = sdf2.format(ytbDetailData.getJssj());
							if("01020000".equals(bankCode)){
								kssjstr = sdf2.format(new Date());
								
							}
							ytbDetailData.setFlag("0");
							//ytbDetailData.setJssj(tbDetailData.getJssj());
							tbDetailData.setKssj(ytbDetailData.getJssj());
							tBankDetailDataDao.saveOrUpdate(ytbDetailData);
							} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				else if(tbDetailData.getQqcslx().equals("07") || tbDetailData.getQqcslx().equals("15")) {
					TBankDetailData ytbDetailData = tBankDetailDataDao.getDetailByPcid(tbDetailData.getYqqdh(),tbDetailData.getZh());
					
					if (ytbDetailData != null) {
						try {
							ytbDetailData.setFlag("0");
							//suwen 2016/02/25 16:09  应该将原冻结数据更新为选中的时间
//							ytbDetailData.setJssj(new Date());
							tBankDetailDataDao.saveOrUpdate(ytbDetailData);
							} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				FreezeAccountElement.addAttribute("KSSJ", transToBase64(isNullToEmpty(kssjstr)));
				// 20151201 - 加入反写数据开始时间
				tbDetailData.setFlag("0");
				tBankDetailDataDao.saveOrUpdate(tbDetailData);
				
				// 明细截至时间
				if (tbDetailData.getJssj() != null) {
					String jzsj = sdf2.format(tbDetailData.getJssj());
					FreezeAccountElement.addAttribute("JSSJ", transToBase64(isNullToEmpty(jzsj)));
				} else {
					FreezeAccountElement.addAttribute("JSSJ", "");
				}

				// String qssj = sdf2.format(tbDetailData.getKssj());
				// String jzsj = sdf2.format(tbDetailData.getJssj());
				// FreezeAccountElement.addAttribute("JSSJ", jzsj); // 结束时间
				// FreezeAccountElement.addAttribute("MXSDLX",
				// String.valueOf(tbDetailData.getZxsjqj())); // 明细时段类型
				// FreezeAccountElement.addAttribute("JSSJ", jzsj);
			}
		}

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");

		// 上传文件的文件路径
		String outFilePath = "";

		// 将xml文件上传
		FileOutputStream fileout = null;
		XMLWriter outXmlWriter = null;
		String outXmlFile = ""; // 输出的xml文件名

		// Map map = new HashMap();

		// String ymdStr = DateUtil.formatToString(new Date()); //年月日格式

		try {
			// 输出到控制台
			// XMLWriter xmlWriter = new XMLWriter();
			// xmlWriter.write(outdocument);

			// outXmlFile = grOrdwXZDJCXSQ+currentDate+bankCode+qqdh+".xml";
			outXmlFile = grOrdwXZDJCXSQ + bankCode + qqdh + ".xml";
			// String outDirPath = "E:\\test";
			String outDirPath = "";
			outDirPath = uploadPath + File.separator + qqdh + File.separator
					+ bankCode; // 带有银行代码的目录路径;
			File outDir = new File(outDirPath);

			if (!outDir.exists()) {
				//System.out.println("不存在目录，则创建");
				outDir.mkdirs();
			}

			outFilePath = outDirPath + File.separator + outXmlFile;

			// 文件目录路径
			// map.put("outDirPath", outDirPath);
			// xml文件路径
			// map.put("outXmlFilePath", outFilePath);

			// 输出XML文件
			fileout = new FileOutputStream(outFilePath);
			outXmlWriter = new XMLWriter(fileout, format);
			outXmlWriter.write(outDocument);

			fileout.flush();
			outXmlWriter.flush();

			fileout.close();
			outXmlWriter.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileout != null)
					fileout.close();
				if (outXmlWriter != null)
					outXmlWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return outFilePath;
	}

	/**
	 * 协助布控查询（账号查询、detail查询）
	 * 
	 * @descriptioin TODO
	 * @param
	 * @return void
	 */
	public String generateXmlForControl(String grOrdwXZBKCXSQ,
			List<TBankDetailData> detailList, TBankDataDemand tbdd,
			String uploadPath, String bankCode, String qqdh) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
		// String currentTime = sdf1.format(new Date());
		// String currentDate = sdf2.format(new Date());
		String fasongTime = sdf3.format(new Date());

		Document outDocument = DocumentHelper.createDocument();
		outDocument.setXMLEncoding("utf-8");
		Element outRoot = DocumentHelper.createElement("CONTROLFORM");
		outDocument.setRootElement(outRoot);

		Element BasicInfoElement = outRoot.addElement("BASICINFO");
		Element QueryPersonElement = outRoot.addElement("QUERYPERSON");

		if (tbdd != null) {

			BasicInfoElement.addAttribute("QQDBS", transToBase64(isNullToEmpty(qqdh))); // 请求单标识 qqdh
			BasicInfoElement.addAttribute("QQCSLX", transToBase64(isNullToEmpty(getCslx(tbdd.getSqlxid())))); // 请求措施类型

			String sqjgdmStr = tbdd.getDeptId();
			if(sqjgdmStr!=null && sqjgdmStr.length()>2){
				sqjgdmStr = sqjgdmStr.substring(0, 2);
				sqjgdmStr = sqjgdmStr+"0000";
			}else{
				sqjgdmStr="";
			}
			BasicInfoElement.addAttribute("SQJGDM", transToBase64(isNullToEmpty(sqjgdmStr))); // 申请机构代码

			BasicInfoElement.addAttribute("MBJGDM", transToBase64(isNullToEmpty(bankCode))); // 目标机构代码
			if ("SS11".equals(grOrdwXZBKCXSQ)) { // 个人
				BasicInfoElement.addAttribute("CKZTLB", transToBase64("01")); // 主体类别
			} else if ("SS12".equals(grOrdwXZBKCXSQ)) { // 单位
				BasicInfoElement.addAttribute("CKZTLB", transToBase64("02"));
			}
			BasicInfoElement.addAttribute("JJCD", transToBase64(isNullToEmpty(String.valueOf(tbdd.getJjcdItemCode())))); // 紧急程度 jjcd
			BasicInfoElement.addAttribute("AJLX", transToBase64(isNullToEmpty(tbdd.getAjlx_name()))); // 案件类型
			BasicInfoElement.addAttribute("BEIZ", transToBase64("")); // 备注
																		// remark
			BasicInfoElement.addAttribute("FSSJ", transToBase64(isNullToEmpty(fasongTime))); // 发送时间

			

			QueryPersonElement.addAttribute("QQRXM", transToBase64(isNullToEmpty(tbdd.getLxrxm1()))); // 请求人姓名
																		// lxrxm1
//			QueryPersonElement.addAttribute("QQRZJLX", EncodeUtils.base64Encode("110031".getBytes())); // 请求人证件类型 -
			QueryPersonElement.addAttribute("QQRZJLX", transToBase64(toZjlxStr(bankCode))); // 请求人证件类型 -
																	// 110031
			QueryPersonElement.addAttribute("QQRZJHM", transToBase64(isNullToEmpty(tbdd.getCbr1()))); // 请求人证件号码（请求人警号
																		// cbr1）
			QueryPersonElement.addAttribute("QQRDWMC", transToBase64(isNullToEmpty(tbdd.getCbdw()))); // 请求人单位名称
																		// cbdw
			QueryPersonElement.addAttribute("QQRSJH", transToBase64(isNullToEmpty(tbdd.getCbr1sjh()))); // 请求人手机号
																			// cbr1sjh
			// QueryPersonElement.addAttribute("QQRJH", tbdd.getCbr1()); //
			// 请求人警号 cbr1

			User user = userService.findUniqueByProperty("userid", tbdd.getCbr2());
			String xcrxm = user.getOperatorname();
			
			QueryPersonElement.addAttribute("XCRXM", transToBase64(isNullToEmpty(xcrxm))); // 协查人姓名
			
//			QueryPersonElement.addAttribute("XCRXM", EncodeUtils.base64Encode(isNullToEmpty(tbdd.getLxrxm2()).getBytes())); // 协查人姓名
																		// lxrxm2
//			QueryPersonElement.addAttribute("XCRZJLX", EncodeUtils.base64Encode("110031".getBytes())); // 协查人证件类型 -
			QueryPersonElement.addAttribute("XCRZJLX", transToBase64(toZjlxStr(bankCode))); // 协查人证件类型 -
																	// 110031
			QueryPersonElement.addAttribute("XCRZJHM", transToBase64(isNullToEmpty(tbdd.getCbr2()))); // 协查人证件号码（协查人警号
																		// cbr2）
			//QueryPersonElement.addAttribute("XCRSJH", tbdd.getCbr2sjh()); // 协查人手机号
																			// cbr2sjh
			// QueryPersonElement.addAttribute("XCRJH", tbdd.getCbr2()); //
			// 协查人警号 cbr2

		}

		Element ControlAccountsElement = outRoot.addElement("CONTROLACCOUNTS");

		if (detailList != null && detailList.size() > 0) {
			for (int i = 0; i < detailList.size(); i++) {
				TBankDetailData tbDetailData = detailList.get(i);

				Element ControlAccountElement = ControlAccountsElement
						.addElement("CONTROLACCOUNT");
				ControlAccountElement.addAttribute("RWLSH", transToBase64(isNullToEmpty(tbDetailData.getRwlsh()))); // 任务流水号
				ControlAccountElement.addAttribute("YRWLSH", transToBase64(isNullToEmpty(tbDetailData.getYrwlsh()))); // 原任务流水号
				ControlAccountElement.addAttribute("ZH", transToBase64(isNullToEmpty(tbDetailData.getZh()))); // 动态查询账号

				ControlAccountElement.addAttribute("ZXSJQJ", EncodeUtils.base64Encode(isNullToEmpty(String.valueOf(tbDetailData.getZxsjqj())).getBytes())); // 执行时间区间
				String zxsjqjStr = String.valueOf(tbDetailData.getZxsjqj());
				// 开始时间（发送时间）
				Date currentDate = new Date();
				String kssjstr = sdf2.format(currentDate);
				Calendar calendar = Calendar.getInstance();
				TBankDetailData ytbDetailData = tBankDetailDataDao.getDetailByPcid(tbDetailData.getYqqdh(),tbDetailData.getZh());
				if (tbDetailData.getQqcslx().equals("03") || tbDetailData.getQqcslx().equals("11")) {

					if (ytbDetailData != null) {
						try {
							Date jssj = ytbDetailData.getJssj();
							//System.out.println(jssj);
							calendar.setTime(jssj);
							if (tbDetailData.getZxsjqj() != null && "1".equals(tbDetailData.getZxsjqj()+"")) {
								calendar.add(Calendar.MONTH, 1);
							} else if (tbDetailData.getZxsjqj() != null
									&& "2".equals(tbDetailData.getZxsjqj()+"")) {
								calendar.add(Calendar.MONTH, 2);
							} else if (tbDetailData.getZxsjqj() != null
									&& "3".equals(tbDetailData.getZxsjqj()+"")) {
								calendar.add(Calendar.MONTH, 3);
							} else if (tbDetailData.getZxsjqj() != null
									&& "4".equals(tbDetailData.getZxsjqj()+"")) {
								// 最多100天
								calendar.add(Calendar.DATE, 100);
							}
							//SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
							
							//System.out.println(sdf1.format(calendar.getTime()));
//							ytbDetailData.setJssj(sdf1.parse(sdf1.format(calendar.getTime())));
							ytbDetailData.setFlag("0");
							//ytbDetailData.setJssj(currentDate);
							tBankDetailDataDao.saveOrUpdate(ytbDetailData);
							// tBankDetailDataDao.updateDetail(tbDetailData.getYqqdh(),
							// tbDetailData.getZh(), jssjStr);
							tbDetailData.setFlag("0");	
							tbDetailData.setKssj(ytbDetailData.getJssj());
							tbDetailData.setJssj(sdf1.parse(sdf1.format(calendar.getTime())));
							tBankDetailDataDao.update(tbDetailData);
							ControlAccountElement.addAttribute("KSSJ", transToBase64(isNullToEmpty(sdf2.format(ytbDetailData.getJssj().getTime()))));	
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				if (tbDetailData.getQqcslx().equals("04") || tbDetailData.getQqcslx().equals("12")) {

					if (ytbDetailData != null) {
						try {
														
//							ytbDetailData.setJssj(new Date());
							ytbDetailData.setFlag("0");
							//ytbDetailData.setJssj(currentDate);
							tBankDetailDataDao.saveOrUpdate(ytbDetailData);
							// tBankDetailDataDao.updateDetail(tbDetailData.getYqqdh(),
							// tbDetailData.getZh(), jssjStr);
							ControlAccountElement.addAttribute("KSSJ", transToBase64(isNullToEmpty(sdf2.format(ytbDetailData.getKssj()))));
							String jssjStr = sdf2.format(ytbDetailData.getJssj());
							ControlAccountElement.addAttribute("JSSJ", transToBase64(isNullToEmpty(jssjStr)));
							tbDetailData.setKssj(ytbDetailData.getKssj());
							tbDetailData.setJssj(ytbDetailData.getJssj());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					tbDetailData.setFlag("0");	
					tBankDetailDataDao.update(tbDetailData);
				}
				else{
					if(tbDetailData.getQqcslx().equals("03")){
						calendar.setTime(ytbDetailData.getJssj());
					}
					ControlAccountElement.addAttribute("KSSJ", transToBase64(isNullToEmpty(sdf2.format(calendar.getTime()))));
					if(zxsjqjStr!=null && "1".equals(zxsjqjStr)){
						calendar.add(Calendar.MONTH , 1);
					}else if(zxsjqjStr!=null && "2".equals(zxsjqjStr)){
						calendar.add(Calendar.MONTH , 2);
					}else if(zxsjqjStr!=null && "3".equals(zxsjqjStr)){
						calendar.add(Calendar.MONTH , 3);
					}else if(zxsjqjStr!=null && "4".equals(zxsjqjStr)){	//最多100天
						calendar.add(Calendar.DATE , 100);
					}
					String jssjStr = sdf2.format(calendar.getTime());
					ControlAccountElement.addAttribute("JSSJ", transToBase64(isNullToEmpty(jssjStr)));
					// 20151201 - 反写 开始时间、结束时间
					tbDetailData.setFlag("0");	
					tbDetailData.setKssj(new Date());
					tbDetailData.setJssj(calendar.getTime());
					tBankDetailDataDao.update(tbDetailData);
				}
					
					
				
//				// 起始时间
//				if (tbDetailData.getKssj() != null) {
//					String kssj = sdf2.format(tbDetailData.getKssj());
//					ControlAccountElement.addAttribute("KSSJ", EncodeUtils.base64Encode(isNullToEmpty(kssj).getBytes()));
//				} else {
//					ControlAccountElement.addAttribute("KSSJ", "");
//				}
//				// 结束时间
//				if (tbDetailData.getJssj() != null) {
//					String jssj = sdf2.format(tbDetailData.getJssj());
//					ControlAccountElement.addAttribute("JSSJ", EncodeUtils.base64Encode(isNullToEmpty(jssj).getBytes()));
//				} else {
//					ControlAccountElement.addAttribute("JSSJ", "");
//				}
				
			}
		}

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");

		// 上传文件的文件路径
		String outFilePath = "";

		// 将xml文件上传
		FileOutputStream fileout = null;
		XMLWriter outXmlWriter = null;
		String outXmlFile = ""; // 输出的xml文件名

		// Map map = new HashMap();

		// String ymdStr = DateUtil.formatToString(new Date()); //年月日格式

		try {
			// 输出到控制台
			// XMLWriter xmlWriter = new XMLWriter();
			// xmlWriter.write(outdocument);

			// outXmlFile = grOrdwXZBKCXSQ+currentDate+bankCode+qqdh+".xml";
			outXmlFile = grOrdwXZBKCXSQ + bankCode + qqdh + ".xml";
			// String outDirPath = "E:\\test";
			String outDirPath = "";
			outDirPath = uploadPath + File.separator + qqdh + File.separator
					+ bankCode; // 带有银行代码的目录路径;
			File outDir = new File(outDirPath);

			if (!outDir.exists()) {
				//System.out.println("不存在目录，则创建");
				outDir.mkdirs();
			}

			outFilePath = outDirPath + File.separator + outXmlFile;

			// 文件目录路径
			// map.put("outDirPath", outDirPath);
			// xml文件路径
			// map.put("outXmlFilePath", outFilePath);

			// 输出XML文件
			fileout = new FileOutputStream(outFilePath);
			outXmlWriter = new XMLWriter(fileout, format);
			outXmlWriter.write(outDocument);

			fileout.flush();
			outXmlWriter.flush();

			fileout.close();
			outXmlWriter.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileout != null)
					fileout.close();
				if (outXmlWriter != null)
					outXmlWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return outFilePath;
	}

	/**
	 * 紧急止付查询（账号查询、detail查询）
	 * 
	 * @descriptioin TODO
	 * @param
	 * @return void
	 */
	public String generateXmlForStopPay(String grOrdwJJZFCXSQ,
			List<TBankDetailData> detailList, TBankDataDemand tbdd,
			String uploadPath, String bankCode, String qqdh) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
		// String currentTime = sdf1.format(new Date());
		// String currentDate = sdf2.format(new Date());
		String fasongTime = sdf3.format(new Date());

		Document outDocument = DocumentHelper.createDocument();
		outDocument.setXMLEncoding("utf-8");
		Element outRoot = DocumentHelper.createElement("STOPPAYMENTFORM");
		outDocument.setRootElement(outRoot);

		Element BasicInfoElement = outRoot.addElement("BASICINFO");
		Element QueryPersonElement = outRoot.addElement("QUERYPERSON");

		if (tbdd != null) {

			BasicInfoElement.addAttribute("QQDBS", transToBase64(isNullToEmpty(qqdh))); // 请求单标识 qqdh
			BasicInfoElement.addAttribute("QQCSLX", transToBase64(isNullToEmpty(getCslx(tbdd.getSqlxid())))); // 请求措施类型

			String sqjgdmStr = tbdd.getDeptId();
			if(sqjgdmStr!=null && sqjgdmStr.length()>2){
				sqjgdmStr = sqjgdmStr.substring(0, 2);
				sqjgdmStr = sqjgdmStr+"0000";
			}else{
				sqjgdmStr="";
			}
			BasicInfoElement.addAttribute("SQJGDM", transToBase64(isNullToEmpty(sqjgdmStr))); // 申请机构代码

			BasicInfoElement.addAttribute("MBJGDM", transToBase64(isNullToEmpty(bankCode))); // 目标机构代码
			if ("SS21".equals(grOrdwJJZFCXSQ)) { // 个人
				BasicInfoElement.addAttribute("CKZTLB", transToBase64("01")); // 主体类别
			} else if ("SS22".equals(grOrdwJJZFCXSQ)) { // 单位
				BasicInfoElement.addAttribute("CKZTLB", transToBase64("02"));
			}
			BasicInfoElement.addAttribute("JJCD", transToBase64(isNullToEmpty(String.valueOf(tbdd.getJjcdItemCode())))); // 紧急程度 jjcd
			BasicInfoElement.addAttribute("AJLX", transToBase64(isNullToEmpty(tbdd.getAjlx_name()))); // 案件类型
			BasicInfoElement.addAttribute("BEIZ", transToBase64("")); // 备注
																		// remark
			BasicInfoElement.addAttribute("FSSJ", transToBase64(isNullToEmpty(fasongTime))); // 发送时间

			

			QueryPersonElement.addAttribute("QQRXM", transToBase64(isNullToEmpty(tbdd.getLxrxm1()))); // 请求人姓名
																		// lxrxm1
//			QueryPersonElement.addAttribute("QQRZJLX", EncodeUtils.base64Encode("110031".getBytes())); // 请求人证件类型 -
			QueryPersonElement.addAttribute("QQRZJLX", transToBase64(toZjlxStr(bankCode))); // 请求人证件类型 -
																	// 110031
			QueryPersonElement.addAttribute("QQRZJHM", transToBase64(isNullToEmpty(tbdd.getCbr1()))); // 请求人证件号码（请求人警号
																		// cbr1）
			QueryPersonElement.addAttribute("QQRDWMC", transToBase64(isNullToEmpty(tbdd.getCbdw()))); // 请求人单位名称
																		// cbdw
			QueryPersonElement.addAttribute("QQRSJH", transToBase64(isNullToEmpty(tbdd.getCbr1sjh()))); // 请求人手机号
																			// cbr1sjh
			// QueryPersonElement.addAttribute("QQRJH", tbdd.getCbr1()); //
			// 请求人警号 cbr1

			User user = userService.findUniqueByProperty("userid", tbdd.getCbr2());
			String xcrxm = user.getOperatorname();
			
			QueryPersonElement.addAttribute("XCRXM", transToBase64(isNullToEmpty(xcrxm))); // 协查人姓名
			
//			QueryPersonElement.addAttribute("XCRXM", EncodeUtils.base64Encode(isNullToEmpty(tbdd.getLxrxm2()).getBytes())); // 协查人姓名
																		// lxrxm2
//			QueryPersonElement.addAttribute("XCRZJLX", EncodeUtils.base64Encode("110031".getBytes())); // 协查人证件类型 -
			QueryPersonElement.addAttribute("XCRZJLX", transToBase64(toZjlxStr(bankCode))); // 协查人证件类型 -
																	// 110031
			QueryPersonElement.addAttribute("XCRZJHM", transToBase64(isNullToEmpty(tbdd.getCbr2()))); // 协查人证件号码（协查人警号
																		// cbr2）
			//QueryPersonElement.addAttribute("XCRSJH", tbdd.getCbr2sjh()); // 协查人手机号
																			// cbr2sjh
			// QueryPersonElement.addAttribute("XCRJH", tbdd.getCbr2()); //
			// 协查人警号 cbr2

		}

		Element StopPaymentAccountsElement = outRoot
				.addElement("STOPPAYMENTACCOUNTS");

		if (detailList != null && detailList.size() > 0) {
			for (int i = 0; i < detailList.size(); i++) {
				TBankDetailData tbDetailData = detailList.get(i);

				Element StopPaymentAccountElement = StopPaymentAccountsElement
						.addElement("STOPPAYMENTACCOUNT");
				StopPaymentAccountElement.addAttribute("RWLSH",transToBase64(isNullToEmpty(tbDetailData.getRwlsh()))); // 任务流水号
				StopPaymentAccountElement.addAttribute("YRWLSH",transToBase64(isNullToEmpty(tbDetailData.getYrwlsh()))); // 原任务流水号
				
				StopPaymentAccountElement.addAttribute("ZH", transToBase64(isNullToEmpty(tbDetailData.getZh()))); // 止付账号
				QueryPersonElement.addAttribute("ZHXH", transToBase64(isNullToEmpty(tbDetailData.getZhzzh()))); //账户序号
//				StopPaymentAccountElement.addAttribute("YQQDBS", EncodeUtils.base64Encode(isNullToEmpty(tbDetailData.getYqqdh()).getBytes())); // 止付账号
				if (tbDetailData.getQqcslx().equals("09") || tbDetailData.getQqcslx().equals("17")) {
					TBankDetailData ytbDetailData = tBankDetailDataDao.getDetailByPcid(tbDetailData.getYqqdh(),tbDetailData.getZh());

					if (ytbDetailData != null) {
						try {
							ytbDetailData.setFlag("0");
							//ytbDetailData.setJssj(new Date());
							tBankDetailDataDao.saveOrUpdate(ytbDetailData);
							} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				// 开始时间（发送时间）
				Date currentDate = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(currentDate);
				calendar.add(Calendar.DATE , 2);
				tbDetailData.setFlag("0");
				// 20151203 - 反写 开始时间、结束时间
				tbDetailData.setKssj(currentDate);
				tbDetailData.setJssj(calendar.getTime());
				tBankDetailDataDao.saveOrUpdate(tbDetailData);
				
			}
		}

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");

		// 上传文件的文件路径
		String outFilePath = "";

		// 将xml文件上传
		FileOutputStream fileout = null;
		XMLWriter outXmlWriter = null;
		String outXmlFile = ""; // 输出的xml文件名

		// Map map = new HashMap();

		// String ymdStr = DateUtil.formatToString(new Date()); //年月日格式

		try {
			// 输出到控制台
			// XMLWriter xmlWriter = new XMLWriter();
			// xmlWriter.write(outdocument);

			// outXmlFile = grOrdwJJZFCXSQ+currentDate+bankCode+qqdh+".xml";
			outXmlFile = grOrdwJJZFCXSQ + bankCode + qqdh + ".xml";
			// String outDirPath = "E:\\test";
			String outDirPath = "";
			outDirPath = uploadPath + File.separator + qqdh + File.separator
					+ bankCode; // 带有银行代码的目录路径;
			File outDir = new File(outDirPath);

			if (!outDir.exists()) {
				//System.out.println("不存在目录，则创建");
				outDir.mkdirs();
			}

			outFilePath = outDirPath + File.separator + outXmlFile;

			// 文件目录路径
			// map.put("outDirPath", outDirPath);
			// xml文件路径
			// map.put("outXmlFilePath", outFilePath);

			// 输出XML文件
			fileout = new FileOutputStream(outFilePath);
			outXmlWriter = new XMLWriter(fileout, format);
			outXmlWriter.write(outDocument);

			fileout.flush();
			outXmlWriter.flush();

			fileout.close();
			outXmlWriter.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileout != null)
					fileout.close();
				if (outXmlWriter != null)
					outXmlWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return outFilePath;
	}

	/**
	 * 身份证查询（账户查询） - 本地测试
	 * 
	 * @descriptioin TODO
	 * @param
	 * @return void
	 */
	public static String getCslx(Long sqlxid)
	{
		String qqcslx="";
		if(sqlxid==101)
		{
			qqcslx="01";
		}else if(sqlxid==102){
			qqcslx="18";
		}
		else if(sqlxid==201)
		{
			qqcslx="05";
		}
		else if(sqlxid==202)
		{
			qqcslx="06";
		}
		else if(sqlxid==203)
		{
			qqcslx="07";
		}
		else if(sqlxid==204)
		{
			qqcslx="13";
		}
		else if(sqlxid==205)
		{
			qqcslx="14";
		}
		else if(sqlxid==206)
		{
			qqcslx="15";
		}
		else if(sqlxid==301)
		{
			qqcslx="02";
		}
		else if(sqlxid==302)
		{
			qqcslx="03";
		}
		else if(sqlxid==303)
		{
			qqcslx="04";
		}
		else if(sqlxid==304)
		{
			qqcslx="10";
		}
		else if(sqlxid==305)
		{
			qqcslx="11";
		}
		else if(sqlxid==306)
		{
			qqcslx="12";
		}
		else if(sqlxid==401)
		{
			qqcslx="08";
		}
		else if(sqlxid==402)
		{
			qqcslx="09";
		}
		else if(sqlxid==403){
			qqcslx="16";
		}
		else if(sqlxid==404){
			qqcslx="17";
		}
		return qqcslx;
	}
	
	/**
	 * 协查XML文件的生成
	 * @descriptioin TODO
	 * @param
	 * @return void
	 */
	public  String generateXmlForAssit(String grOrdwZHXXCXSQ,  
			List<TBankPersonData> personList, TBankDataDemand tbdd,
			List<TBankDetailData> detail,List<TBankAccesorry> accesorry,
			String uploadPath, String bankCode,String qqdh ,String sqlxid) {
		//String sqlxid=grOrdwZHXXCXSQ.substring(2,5);
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String fasongTime = sdf3.format(new Date());
		
		Document outDocument = DocumentHelper.createDocument();
		outDocument.setXMLEncoding("utf-8");
		Element outRoot = DocumentHelper.createElement("ASSISTFORM");
		outDocument.setRootElement(outRoot);

		Element BasicInfoElement = outRoot.addElement("BASICINFO");
		Element  PersonsElement = outRoot.addElement("PERSONS");
		Element  AccountElement = outRoot.addElement("ACCOUNTS");
		//TODO 文书
		Element WsinfoElement = outRoot.addElement("WSINFOS");
		//审核意见
		Element ExamineElement=outRoot.addElement("EXAMINEOPTIONS");
		if (tbdd != null) {
			BasicInfoElement.addAttribute("QQDH", transToBase64(tbdd.getQqdh())); // 请求单标识 qqdh
			BasicInfoElement.addAttribute("SQZT", transToBase64("11"));  
			if(tbdd.getCreatetime()!=null&&!"".equals(tbdd.getCreatetime())){//创建时间
				BasicInfoElement.addAttribute("CREATETIME", transToBase64(sdf3.format(tbdd.getCreatetime())));  
			}
			if(tbdd.getCreator()!=null&&!"".equals(tbdd.getCreator())){//创建人
				BasicInfoElement.addAttribute("CREATOR", transToBase64(tbdd.getCreator().toString()));  
			}else{
				BasicInfoElement.addAttribute("CREATOR", "");  
			}
			if(tbdd.getUpdatetime()!=null&&!"".equals(tbdd.getUpdatetime())){//更新时间
				BasicInfoElement.addAttribute("UPDATETIME", transToBase64(sdf3.format(tbdd.getUpdatetime())));  
			}else{
				BasicInfoElement.addAttribute("UPDATETIME", "");  
			}
			if(tbdd.getUpdator()!=null&&!"".equals(tbdd.getUpdator())){//更新人员
				BasicInfoElement.addAttribute("UPDATOR", transToBase64(tbdd.getUpdator().toString()));  
			}else{
				BasicInfoElement.addAttribute("UPDATOR", "");  
			}
			if(tbdd.getSqlxid()!=null&&!"".equals(tbdd.getSqlxid())){//申请类型ID
				BasicInfoElement.addAttribute("SQLXID",transToBase64(tbdd.getSqlxid().toString()));  
			}else{
				BasicInfoElement.addAttribute("SQLXID", "");  
			}
			if(tbdd.getDeptId()!=null&&!"".equals(tbdd.getDeptId())){//创建者部门iD
				BasicInfoElement.addAttribute("CREATOR_DEPT_ID", transToBase64(tbdd.getDeptId()));  
			}else{
				BasicInfoElement.addAttribute("CREATOR_DEPT_ID", "");  
			}
			if(tbdd.getSxzmName()!=null&&!"".equals(tbdd.getSxzmName())){//涉嫌罪名
				BasicInfoElement.addAttribute("SXZM", transToBase64(tbdd.getSxzmName()));  
			}else{
				BasicInfoElement.addAttribute("SXZM", "");  
			}
			if(tbdd.getAjmc()!=null&&!"".equals(tbdd.getAjmc())){//案件名称
				BasicInfoElement.addAttribute("AJMC", transToBase64(tbdd.getAjmc()));  
			}else{
				BasicInfoElement.addAttribute("AJMC", "");  
			}
			if(tbdd.getAjzt()!=null&&!"".equals(tbdd.getAjzt())){//案件状态
				BasicInfoElement.addAttribute("AJZT",  transToBase64(String.valueOf(tbdd.getAjzt())));
			}else{
				BasicInfoElement.addAttribute("AJZT", "");  
			}
			
			if(tbdd.getAjbh()!=null&&!"".equals(tbdd.getAjbh())){//案件编号
				BasicInfoElement.addAttribute("AJBH",  transToBase64(tbdd.getAjbh()));  
			}else{
				BasicInfoElement.addAttribute("AJBH", "");  
			}
			if(tbdd.getLasj()!=null&&!"".equals(tbdd.getLasj())){//立案时间
				BasicInfoElement.addAttribute("LASJ", transToBase64(sdf2.format(tbdd.getLasj())));  
			}else{
				BasicInfoElement.addAttribute("LASJ", "");  
			}
			if(tbdd.getJyaq()!=null&&!"".equals(tbdd.getJyaq())){//简易案情
				BasicInfoElement.addAttribute("JYAQ",transToBase64(tbdd.getJyaq()));  
			}else{
				BasicInfoElement.addAttribute("JYAQ", "");  
			}
			if(tbdd.getCbdw()!=null&&!"".equals(tbdd.getCbdw())){//承办单位
				BasicInfoElement.addAttribute("CBDW",transToBase64(tbdd.getCbdw()));  
			}else{
				BasicInfoElement.addAttribute("CBDW", "");  
			}
			if(tbdd.getCbr1()!=null&&!"".equals(tbdd.getCbr1())){//承办人1
				BasicInfoElement.addAttribute("CBR1", transToBase64(tbdd.getCbr1()));  
			}else{
				BasicInfoElement.addAttribute("CBR1", "");  
			}
			if(tbdd.getCbr2()!=null&&!"".equals(tbdd.getCbr2())){//承办人2
				BasicInfoElement.addAttribute("CBR2", transToBase64(tbdd.getCbr2()));  
			}else{
				BasicInfoElement.addAttribute("CBR2", "");  
			}
			if(tbdd.getJjcd()!=null&&!"".equals(tbdd.getJjcd())){//紧急程度
				BasicInfoElement.addAttribute("JJCD",  transToBase64(String.valueOf(tbdd.getJjcd())));  //tbdd.getJjcd().toString()
			}else{
				BasicInfoElement.addAttribute("JJCD", "");  
			}
			if(tbdd.getRemark()!=null&&!"".equals(tbdd.getRemark())){//备注
				BasicInfoElement.addAttribute("REMARK", transToBase64(tbdd.getRemark()));  
			}else{
				BasicInfoElement.addAttribute("REMARK", "");  
			}
			if(tbdd.getFlowNo()!=null&&!"".equals(tbdd.getFlowNo())){//流程编号
				BasicInfoElement.addAttribute("LCBH", transToBase64(tbdd.getFlowNo()));  
			}else{
				BasicInfoElement.addAttribute("LCBH", "");
			}
			if(tbdd.getAjlx()!=null&&!"".equals(tbdd.getAjlx())){//案件类型
				BasicInfoElement.addAttribute("AJLX",transToBase64(String.valueOf(tbdd.getAjlx())));  // tbdd.getAjlx().toString()
			}else{
				BasicInfoElement.addAttribute("AJLX", "");  
			}
			if(tbdd.getFlowRange()!=null&&!"".equals(tbdd.getFlowRange())){//流程范围
				BasicInfoElement.addAttribute("LCFW", transToBase64(tbdd.getFlowRange()));  
			}else{
				BasicInfoElement.addAttribute("LCFW", "");
			}
			if(tbdd.getZbcdeptid()!=null&&!"".equals(tbdd.getZbcdeptid())){//主办处部门ID
				BasicInfoElement.addAttribute("ZBCDEPTID", transToBase64(tbdd.getZbcdeptid()));  
			}else{
				BasicInfoElement.addAttribute("ZBCDEPTID", "");
			}
			if(tbdd.getGjwh()!=null&&!"".equals(tbdd.getGjwh())){//公经文号
				BasicInfoElement.addAttribute("GJWH", transToBase64(tbdd.getGjwh().toString()));  
			}else{
				BasicInfoElement.addAttribute("GJWH", "");
			}
			if(tbdd.getCbr1sjh()!=null&&!"".equals(tbdd.getCbr1sjh())){//承办人1手机号
				BasicInfoElement.addAttribute("CBR1SJH", transToBase64(tbdd.getCbr1sjh()));  
			}else{
				BasicInfoElement.addAttribute("CBR1SJH", "");
			}
			if(tbdd.getCbr1dwmc()!=null&&!"".equals(tbdd.getCbr1dwmc())){//承办人1单位名称
				BasicInfoElement.addAttribute("CBR1DWMC", transToBase64(tbdd.getCbr1dwmc()));  
			}else{
				BasicInfoElement.addAttribute("CBR1DWMC", "");
			}
			if(tbdd.getCbr1dh()!=null&&!"".equals(tbdd.getCbr1dh())){//承办人1电话
				BasicInfoElement.addAttribute("CBR1DH",transToBase64(tbdd.getCbr1dh()));  
			}else{
				BasicInfoElement.addAttribute("CBR1DH", "");
			}
			if(tbdd.getCbr1dz()!=null&&!"".equals(tbdd.getCbr1dz())){//承办人1地址
				BasicInfoElement.addAttribute("CBR1DZ", transToBase64(tbdd.getCbr1dh()));  
			}else{
				BasicInfoElement.addAttribute("CBR1DZ", "");
			}
			if(tbdd.getCbr2sjh()!=null&&!"".equals(tbdd.getCbr2sjh())){//承办人2手机号
				BasicInfoElement.addAttribute("CBR2SJH", transToBase64(tbdd.getCbr2sjh()));  
			}else{
				BasicInfoElement.addAttribute("CBR2SJH", "");
			}
			if(tbdd.getCbr2dh()!=null&&!"".equals(tbdd.getCbr2dh())){//承办人2电话
				BasicInfoElement.addAttribute("CBR2DH",transToBase64(tbdd.getCbr2dh()));  
			}else{
				BasicInfoElement.addAttribute("CBR2DH","");  
			}
			if(tbdd.getCbr1dz()!=null&&!"".equals(tbdd.getCbr1dz())){//承办人2地址
				BasicInfoElement.addAttribute("CBR2DZ", transToBase64(tbdd.getCbr1dh()));  
			}else{
				BasicInfoElement.addAttribute("CBR2DZ", "");
			}
			if(tbdd.getCbr2dwmc()!=null&&!"".equals(tbdd.getCbr2dwmc())){//承办人2单位名称
				BasicInfoElement.addAttribute("CBR2DWMC", transToBase64(tbdd.getCbr2dwmc()));  
			}else{
				BasicInfoElement.addAttribute("CBR2DWMC", "");
			}
			if(tbdd.getGjnh()!=null&&!"".equals(tbdd.getGjnh())){//公经年号
				BasicInfoElement.addAttribute("GJNH", transToBase64(tbdd.getGjnh().toString()));  
			}else{
				BasicInfoElement.addAttribute("GJNH", "");
			}
			if(tbdd.getCxrId()!=null&&!"".equals(tbdd.getCxrId())){//查办人ID
				BasicInfoElement.addAttribute("CXRID", transToBase64(tbdd.getCxrId().toString()));  
			}else{
				BasicInfoElement.addAttribute("CXRID", "");
			}
			if(tbdd.getJbtzwh()!=null&&!"".equals(tbdd.getJbtzwh())){//交办通知文号
				BasicInfoElement.addAttribute("JBTZWH", transToBase64(tbdd.getJbtzwh().toString()));  
			}else{
				BasicInfoElement.addAttribute("JBTZWH", "");
			}
			if(tbdd.getGlghajs()!=null&&!"".equals(tbdd.getGlghajs())){//关联工行历史申请数
				BasicInfoElement.addAttribute("GLGHAJS", transToBase64(tbdd.getGlghajs().toString()));  
			}else{
				BasicInfoElement.addAttribute("GLGHAJS", "");
			}
			if(tbdd.getLzztid()!=null&&!"".equals(tbdd.getLzztid())){//流转状态ID
				BasicInfoElement.addAttribute("LZZTID", transToBase64(tbdd.getLzztid().toString()));  
			}else{
				BasicInfoElement.addAttribute("LZZTID", "");
			}
			if(tbdd.getLxrxm1()!=null&&!"".equals(tbdd.getLxrxm1())){//联系人姓名1
				BasicInfoElement.addAttribute("LXRXM1",transToBase64(tbdd.getLxrxm1()));  
			}else{
				BasicInfoElement.addAttribute("LXRXM1", "");
			}
			if(tbdd.getLxrxm2()!=null&&!"".equals(tbdd.getLxrxm2())){//联系人姓名2
				BasicInfoElement.addAttribute("LXRXM2", transToBase64(tbdd.getLxrxm2()));  
			}else{
				BasicInfoElement.addAttribute("LXRXM2", "");
			}
			if(tbdd.getMj()!=null&&!"".equals(tbdd.getMj())){//密级
				BasicInfoElement.addAttribute("MJ", transToBase64(tbdd.getMj())); 
			}else{
				BasicInfoElement.addAttribute("MJ", "");
			}
			//内外部申请
				BasicInfoElement.addAttribute("INOUTID",transToBase64("102"));
			if(tbdd.getCxsqid()!=null&&!"".equals(tbdd.getCxsqid())){//数据查询申请ID
				BasicInfoElement.addAttribute("CXSQID", transToBase64(String.valueOf(tbdd.getCxsqid())));
			}else{
				BasicInfoElement.addAttribute("CXSQID", "");
			}
			if(tbdd.getProvicecode()!=null&&!"".equals(tbdd.getProvicecode())){//申请发起地
				BasicInfoElement.addAttribute("PROVICECODE", transToBase64(String.valueOf(tbdd.getProvicecode())));
			}else{
				BasicInfoElement.addAttribute("PROVICECODE", "");
			}
			if(tbdd.getJldjh()!=null&&!"".equals(tbdd.getJldjh())){
				BasicInfoElement.addAttribute("JLDJH",transToBase64(tbdd.getJldjh()));
			}else{
				BasicInfoElement.addAttribute("JLDJH", "");
			}
			if(tbdd.getFeedBackCode()!=null&&!"".equals(tbdd.getFeedBackCode())){//接收申请公安机关代码
				BasicInfoElement.addAttribute("FEEDBACKCODE", transToBase64(tbdd.getFeedBackCode()));
			}else{
				BasicInfoElement.addAttribute("FEEDBACKCODE", "");
			}
			//if(tbdd.getAssistTypeStr()!=null&&!"".equals(tbdd.getAssistTypeStr())){//协查申请类型（01请求、02反馈）
				BasicInfoElement.addAttribute("ASSISTTYPE", transToBase64("请求"));
/*			}else{
				BasicInfoElement.addAttribute("ASSISTTYPE", " ");
			}*/
		}	
		 
			if (personList != null && personList.size() > 0) {
				for (int i = 0; i < personList.size(); i++) {
					Element personElement = PersonsElement.addElement("PERSON");
					TBankPersonData tbPersonData = personList.get(i);
					personElement.addAttribute("PCID",transToBase64(tbPersonData.getPcid()));//批次ID
					if(tbPersonData.getXm()!=null&&!"".equals(tbPersonData.getXm())){//涉案对象名称
						personElement.addAttribute("XM", transToBase64(tbPersonData.getXm()));
					}else{
						BasicInfoElement.addAttribute("XM", "");
					}
					if(tbPersonData.getZjlx()!=null&&!"".equals(tbPersonData.getZjlx())){//证件类型
						personElement.addAttribute("ZJLX", transToBase64(tbPersonData.getZjlxCode()));
					}else{
						BasicInfoElement.addAttribute("ZJLX", "");
					}
					if(tbPersonData.getZjhm()!=null&&!"".equals(tbPersonData.getZjhm())){//证件号码
						personElement.addAttribute("ZJHM", transToBase64(tbPersonData.getZjhm()));
					}else{
						BasicInfoElement.addAttribute("ZJHM", "");
					}
					if(tbPersonData.getZhxx()!=null&&!"".equals(tbPersonData.getZhxx())){//账户信息
						personElement.addAttribute("ZHXX", transToBase64(tbPersonData.getZjhm()));
					}else{
						BasicInfoElement.addAttribute("ZHXX", "");
					}
					if(tbPersonData.getKhxx()!=null&&!"".equals(tbPersonData.getKhxx())){//开户信息
						personElement.addAttribute("KHXX", transToBase64(tbPersonData.getKhxx()));
					}else{
						BasicInfoElement.addAttribute("KHXX", "");
					}
					if(tbPersonData.getZhmx()!=null&&!"".equals(tbPersonData.getZhmx())){//账户明细
						personElement.addAttribute("ZHMX", transToBase64(tbPersonData.getZhmx()));
					}else{
						BasicInfoElement.addAttribute("ZHMX", "");
					}
					if(tbPersonData.getCxzl()!=null&&!"".equals(tbPersonData.getCxzl())){//查询种类
						personElement.addAttribute("CXZL", transToBase64(tbPersonData.getCxzl()));
					}else{
						BasicInfoElement.addAttribute("CXZL", "");
					}
					if(tbPersonData.getSadxlx()!=null&&!"".equals(tbPersonData.getSadxlx())){//涉案对象类型
						personElement.addAttribute("SADXLX", transToBase64(tbPersonData.getSadxlx()));
					}else{
						BasicInfoElement.addAttribute("SADXLX", "");
					}
					if(tbPersonData.getKssj()!=null&&!"".equals(tbPersonData.getKssj())){//开始时间
						personElement.addAttribute("KSSJ", transToBase64(sdf3.format(tbPersonData.getKssj())));
					}else{
						personElement.addAttribute("KSSJ", "");
					}
					if(tbPersonData.getKssj()!=null&&!"".equals(tbPersonData.getKssj())){//结束时间
						personElement.addAttribute("JSSJ", transToBase64(sdf3.format(tbPersonData.getJssj())));
					}else{
						personElement.addAttribute("JSSJ", "");
					}
					personElement.addAttribute("SFRK",  transToBase64(String.valueOf(tbPersonData.getSfrk())));//是否入库
					if(tbPersonData.getGllsajs()!=null&&!"".equals(tbPersonData.getGllsajs())){//关联历史案件
						personElement.addAttribute("GLLSAJS", transToBase64(String.valueOf(tbPersonData.getGllsajs())));
					}else{
						personElement.addAttribute("GLLSAJS", "");
					}
					if(tbPersonData.getGlghsqs()!=null&&!"".equals(tbPersonData.getGlghsqs())){//关联历史申请数
						personElement.addAttribute("GLGHSQS", transToBase64(String.valueOf(tbPersonData.getGlghsqs())));//  tbPersonData.getGlghsqs().toString()
					}else{
						personElement.addAttribute("GLGHSQS", "");
					}
					if(tbPersonData.getLb()!=null&&!"".equals(tbPersonData.getLb())){//类别
						personElement.addAttribute("LB",  transToBase64(tbPersonData.getLb()));
					}else{
						personElement.addAttribute("LB", "");
					}
					if(tbPersonData.getXz()!=null&&!"".equals(tbPersonData.getXz())){//性质
						personElement.addAttribute("XZ",  transToBase64(tbPersonData.getXz()));
					}else{
						personElement.addAttribute("XZ",  "");
					}
					if(tbPersonData.getJe()!=null&&!"".equals(tbPersonData.getJe())){//金额
						personElement.addAttribute("JE", transToBase64(Commons.getJeStr(tbPersonData.getJe())));//  tbPersonData.getGlghsqs().toString()
					}else{
						personElement.addAttribute("JE",  "");
					}
					if(tbPersonData.getBz()!=null&&!"".equals(tbPersonData.getBz())){//币种
						personElement.addAttribute("BZ", transToBase64(tbPersonData.getBz()));
					}else{
						personElement.addAttribute("BZ",  "");
					}
					if(tbPersonData.getZxsjqj()!=null&&!"".equals(tbPersonData.getZxsjqj())){//执行时间区间
						personElement.addAttribute("ZXSJQJ", transToBase64(String.valueOf(tbPersonData.getZxsjqj())));
					}else{
						personElement.addAttribute("ZXSJQJ",  "");
					}
					if(tbPersonData.getQqcslx()!=null&&!"".equals(tbPersonData.getQqcslx())){//请求措施类型
						personElement.addAttribute("QQCSLX",  transToBase64(tbPersonData.getQqcslx()));
					}else{
						personElement.addAttribute("QQCSLX",  "");
					}
					if(tbPersonData.getSjbs()!=null&&!"".equals(tbPersonData.getSjbs())){//时间标识
						personElement.addAttribute("SJBS",  transToBase64(tbPersonData.getSjbs()));
					}else{
						personElement.addAttribute("SJBS",  "");
					}
					if(tbPersonData.getRwlsh()!=null&&!"".equals(tbPersonData.getRwlsh())){//任务流水号
						personElement.addAttribute("RWLSH",  transToBase64(tbPersonData.getRwlsh()));
					}else{
						personElement.addAttribute("RWLSH",  "");
					}
					if(tbPersonData.getYsjid()!=null&&!"".equals(tbPersonData.getYsjid())){//原数据ID
						personElement.addAttribute("YSJID",  transToBase64(tbPersonData.getYsjid().toString()));
					}else{
						personElement.addAttribute("YSJID",  "");
					}
				}
			}
				if (detail != null && detail.size() > 0) {
					for (int i = 0; i < detail.size(); i++) {
						Element accountElement = AccountElement.addElement("ACCOUNT");
						TBankDetailData tbdetailData = detail.get(i);
						accountElement.addAttribute("PCID", transToBase64(tbdetailData.getPcid()));//批次id
						if(tbdetailData.getZh()!=null&&!"".equals(tbdetailData.getZh())){//账户
							accountElement.addAttribute("ZH", transToBase64(tbdetailData.getZh()));
						}else{
							accountElement.addAttribute("ZH",  "");
						}
						if(tbdetailData.getKssj()!=null&&!"".equals(tbdetailData.getKssj())){//开始时间
							accountElement.addAttribute("KSSJ", transToBase64(sdf3.format(tbdetailData.getKssj())));
						}else{
							accountElement.addAttribute("KSSJ",  "");
						}
						if(tbdetailData.getJssj()!=null&&!"".equals(tbdetailData.getJssj())){//结束时间
							accountElement.addAttribute("JSSJ", transToBase64(sdf3.format(tbdetailData.getJssj())));
						}else{
							accountElement.addAttribute("JSSJ",  "");
						}
						if(tbdetailData.getZhlx()!=null&&!"".equals(tbdetailData.getZhlx())){//账户类型
							accountElement.addAttribute("ZHLX",transToBase64(tbdetailData.getZhlx()));
						}else{
							accountElement.addAttribute("ZHLX",  "");
						}
						accountElement.addAttribute("SFRK", String.valueOf(tbdetailData.getSfrk()) );//是否入库
						if(tbdetailData.getGllsajs()!=null&&!"".equals(tbdetailData.getGllsajs())){
							accountElement.addAttribute("GLLSAJS",transToBase64(String.valueOf(tbdetailData.getGllsajs())));// 关联历史案件数
						}else{
							accountElement.addAttribute("GLLSAJS",  "");
						}
						if(tbdetailData.getGlghsqs()!=null&&!"".equals(tbdetailData.getGlghsqs())){
							accountElement.addAttribute("GLGHSQS",transToBase64(String.valueOf(tbdetailData.getGlghsqs())) );// 关联历史申请数
						}else{
							accountElement.addAttribute("GLGHSQS",  "");
						}
						if(tbdetailData.getBankcode()!=null&&!"".equals(tbdetailData.getBankcode())){//银行编码
							accountElement.addAttribute("BANKCODE", transToBase64( tbdetailData.getBankcode()));
						}else{
							accountElement.addAttribute("BANKCODE",  "");
						}
						if(tbdetailData.getLb()!=null&&!"".equals(tbdetailData.getLb())){//类别
							accountElement.addAttribute("LB",  transToBase64(tbdetailData.getLb()));
						}else{
							accountElement.addAttribute("LB",  "");
						}
						if(tbdetailData.getCxnr()!=null&&!"".equals(tbdetailData.getCxnr())){//查询内容
							accountElement.addAttribute("CXNR",  transToBase64(tbdetailData.getCxnr()));
						}else{
							accountElement.addAttribute("CXNR",  "");
						}
						if(tbdetailData.getJe()!=null&&!"".equals(tbdetailData.getJe())){//金额
							accountElement.addAttribute("JE", transToBase64(String.valueOf(tbdetailData.getJe())));
						}else{
							accountElement.addAttribute("JE",  "");
						}
						if(tbdetailData.getBz()!=null&&!"".equals(tbdetailData.getBz())){//币种
							accountElement.addAttribute("BZ", transToBase64(tbdetailData.getBz()));
						}else{
							accountElement.addAttribute("BZ",  "");
						}
						if(tbdetailData.getZxsjqj()!=null&&!"".equals(tbdetailData.getZxsjqj())){//执行时间区间
							accountElement.addAttribute("ZXSJQJ", transToBase64(tbdetailData.getZxsjqj().toString()));
						}else{
							accountElement.addAttribute("ZXSJQJ",  "");
						}
						if(tbdetailData.getQqcslx()!=null&&!"".equals(tbdetailData.getQqcslx())){//请求措施类型
							accountElement.addAttribute("QQCSLX",  transToBase64(tbdetailData.getQqcslx()));
						}else{
							accountElement.addAttribute("QQCSLX",  "");
						}
						if(tbdetailData.getDjzhhz()!=null&&!"".equals(tbdetailData.getDjzhhz())){//冻结账户户主
							accountElement.addAttribute("DJZHHZ",  transToBase64(tbdetailData.getDjzhhz()));
						}else{
							accountElement.addAttribute("DJZHHZ",  "");
						}
						if(tbdetailData.getZjlx()!=null&&!"".equals(tbdetailData.getZjlx())){//证件类型
							accountElement.addAttribute("ZJLX",  transToBase64(tbdetailData.getZjlx()));
						}else{
							accountElement.addAttribute("ZJLX",  "");
						}
						if(tbdetailData.getZjhm()!=null&&!"".equals(tbdetailData.getZjhm())){//证件号码
							accountElement.addAttribute("ZJHM",  transToBase64(tbdetailData.getZjhm()));
						}else{
							accountElement.addAttribute("ZJHM",  "");
						}
						if(tbdetailData.getSjbs()!=null&&!"".equals(tbdetailData.getSjbs())){//时间标识
							accountElement.addAttribute("SJBS",  transToBase64(tbdetailData.getSjbs()));
						}else{
							accountElement.addAttribute("SJBS",  "");
						}
						if(tbdetailData.getYqqdh()!=null&&!"".equals(tbdetailData.getYqqdh())){//原请求单号
							accountElement.addAttribute("YQQDH",  transToBase64(tbdetailData.getYqqdh()));
						}else{
							accountElement.addAttribute("YQQDH",  "");
						}
						if(tbdetailData.getFlag()!=null&&!"".equals(tbdetailData.getFlag())){//解续冻标识
							accountElement.addAttribute("FLAG",  transToBase64(tbdetailData.getFlag()));
						}else{
							accountElement.addAttribute("FLAG",  "");
						}
						if(tbdetailData.getRwlsh()!=null&&!"".equals(tbdetailData.getRwlsh())){//任务流水号
							accountElement.addAttribute("RWLSH", transToBase64(tbdetailData.getRwlsh()) );
						}else{
							accountElement.addAttribute("RWLSH",  "");
						}
						if(tbdetailData.getYrwlsh()!=null&&!"".equals(tbdetailData.getYrwlsh())){//任务流水号
							accountElement.addAttribute("YRWLSH",  transToBase64(tbdetailData.getYrwlsh()));
						}else{
							accountElement.addAttribute("YRWLSH",  "");
						}
						if(tbdetailData.getYsjid()!=null&&!"".equals(tbdetailData.getYsjid())){//原数据ID
							accountElement.addAttribute("YSJID",  transToBase64(tbdetailData.getYsjid().toString()));
						}else{
							accountElement.addAttribute("YSJID",  "");
						}
					}
				}
				if(accesorry!=null&& accesorry.size()>0){
				for (int i = 0; i < accesorry.size(); i++) {
					Element wsinfo = WsinfoElement.addElement("WSINFO");
					TBankAccesorry bankAccesorry = accesorry.get(i);
					wsinfo.addAttribute("PCID", transToBase64(bankAccesorry.getPcid()));
					if(bankAccesorry.getLslj()!=null&&!"".equals(bankAccesorry.getLslj())){//文件地址
						wsinfo.addAttribute("LSLJ", transToBase64(bankAccesorry.getLslj()));
					}else{
						wsinfo.addAttribute("LSLJ", "");
					}
					if(bankAccesorry.getFjlx()!=null&&!"".equals(bankAccesorry.getFjlx())){//附件类型
						wsinfo.addAttribute("FJLX", transToBase64(bankAccesorry.getFjlx()));
					}else{
						wsinfo.addAttribute("FJLX", "");
					}
					if(bankAccesorry.getFjscryid()!=null&&!"".equals(bankAccesorry.getFjscryid())){//附件上传人员ID
						wsinfo.addAttribute("FJSCRYID",  transToBase64(String.valueOf(bankAccesorry.getFjscryid())));
					}else{
						wsinfo.addAttribute("FJSCRYID", "");
					}
					if(bankAccesorry.getFjscsj()!=null&&!"".equals(bankAccesorry.getFjscsj())){//附件上传时间
						wsinfo.addAttribute("FJSCSJ", transToBase64(sdf2.format(bankAccesorry.getFjscsj())));
					}else{
						wsinfo.addAttribute("FJSCSJ", "");
					}
					if(bankAccesorry.getFjssztlx()!=null&&!"".equals(bankAccesorry.getFjssztlx())){//附件所属状态类型
						wsinfo.addAttribute("FJSSZTLX", transToBase64(bankAccesorry.getFjssztlx()));
					}else{
						wsinfo.addAttribute("FJSSZTLX", "");
					}
					if(bankAccesorry.getFjmc()!=null&&!"".equals(bankAccesorry.getFjmc())){//附件名称
						wsinfo.addAttribute("FJMC", transToBase64(bankAccesorry.getFjmc()));
					}else{
						wsinfo.addAttribute("FJMC", "");
					}
					if(bankAccesorry.getFjlsmc()!=null&&!"".equals(bankAccesorry.getFjlsmc())){//附件临时名称
						wsinfo.addAttribute("FJLSMC", transToBase64(bankAccesorry.getFjlsmc()));
					}else{
						wsinfo.addAttribute("FJLSMC", "");
					}
					if(bankAccesorry.getIsflg()!=null&&!"".equals(bankAccesorry.getIsflg())){//是否入库
						wsinfo.addAttribute("ISFLG", transToBase64(bankAccesorry.getIsflg()));
					}else{
						wsinfo.addAttribute("ISFLG", "");
					}
					if(bankAccesorry.getAjid()!=null&&!"".equals(bankAccesorry.getAjid())){//案件ID
						wsinfo.addAttribute("AJID", transToBase64(bankAccesorry.getAjid()));
					}else{
						wsinfo.addAttribute("AJID", "");
					}
					if(bankAccesorry.getSjlyids()!=null&&!"".equals(bankAccesorry.getSjlyids())){//数据来源ID
						wsinfo.addAttribute("SJLYIDS", transToBase64(bankAccesorry.getSjlyids()));
					}else{
						wsinfo.addAttribute("SJLYIDS", "");
					}
					if(bankAccesorry.getBankcode()!=null&&!"".equals(bankAccesorry.getBankcode())){//银行编号
						wsinfo.addAttribute("BANKCODE", transToBase64(bankAccesorry.getBankcode()));
					}else{
						wsinfo.addAttribute("BANKCODE", "");
					}
					if(bankAccesorry.getCxsqfjid()!=null&&!"".equals(bankAccesorry.getCxsqfjid())){//查询申请附件ID
						wsinfo.addAttribute("CXSQFJID",  transToBase64(String.valueOf(bankAccesorry.getCxsqfjid())));
					}else{
						wsinfo.addAttribute("CXSQFJID", "");
					}
					if(bankAccesorry.getCxsqid()!=null&&!"".equals(bankAccesorry.getCxsqid())){//查询申请ID
						wsinfo.addAttribute("CXSQID", transToBase64(bankAccesorry.getCxsqid().toString()));
					}else{
						wsinfo.addAttribute("CXSQID", "");
					}
				}
				}
				if(!sqlxid.equals("403") || !sqlxid.equals("404")){
					List<ExamineOption> examine= examineOptionDao.getExaminEchart(qqdh);
					if(examine!=null&&examine.size()>0){
						for (int i = 0; i < examine.size(); i++){
							Element examineoption  = ExamineElement.addElement("EXAMINEOPTION ");
							ExamineOption examineOption=examine.get(i);
							examineoption.addAttribute("QQDH", transToBase64(examineOption.getQqdh()));
							//审核人员名称
							if(examineOption.getShrymc()!=null&&!"".equals(examineOption.getShrymc())){
								examineoption.addAttribute("SHRYMC", transToBase64(examineOption.getShrymc()));
							}else{
								examineoption.addAttribute("SHRYMC", "");
							}
							if(examineOption.getShryjh()!=null&&!"".equals(examineOption.getShryjh())){//审核人警号
								examineoption.addAttribute("SHRYJH", transToBase64(examineOption.getShryjh()));
							}else{
								examineoption.addAttribute("SHRYJH", "");
							}
							if(examineOption.getShrjgmc()!=null&&!"".equals(examineOption.getShrjgmc())){//审核人机关名称
								examineoption.addAttribute("SHRYJGMC", transToBase64(examineOption.getShrjgmc()));
							}else{
								examineoption.addAttribute("SHRYJGMC", "");
							}
							if(examineOption.getShrq()!=null&&!"".equals(examineOption.getShrq())){//审核日期
								examineoption.addAttribute("SHRQ", transToBase64(sdf3.format(examineOption.getShrq())));
							}else{
								examineoption.addAttribute("SHRQ", "");
							}
							if(examineOption.getZbrjh()!=null&&!"".equals(examineOption.getZbrjh())){//主办人警号
								examineoption.addAttribute("ZBRJH", transToBase64(examineOption.getZbrjh()));
							}else{
								examineoption.addAttribute("ZBRJH", "");
							}
							if(examineOption.getZbrq()!=null&&!"".equals(examineOption.getZbrq())){//主办日期
								examineoption.addAttribute("ZBRQ", transToBase64(sdf1.format(examineOption.getZbrq())));
							}else{
								examineoption.addAttribute("ZBRQ", "");
							}
							if(examineOption.getShyj()!=null&&!"".equals(examineOption.getShyj())){//审核意见
								examineoption.addAttribute("SHYJ", transToBase64(examineOption.getShyj()));
							}else{
								examineoption.addAttribute("SHYJ", "");
							}
							if(examineOption.getShzt()!=null&&!"".equals(examineOption.getShzt())){//审核状态
								examineoption.addAttribute("SHZT", transToBase64(examineOption.getShzt()));
							}else{
								examineoption.addAttribute("SHZT", "");
							}
							if(examineOption.getYjlx()!=null&&!"".equals(examineOption.getYjlx())){//意见类型
								examineoption.addAttribute("YJLX", transToBase64(examineOption.getYjlx()));
							}else{
								examineoption.addAttribute("YJLX", "");
							}
							if(examineOption.getLcxid()!=null&&!"".equals(examineOption.getLcxid())){//流程项ID
								examineoption.addAttribute("LCXID", transToBase64(examineOption.getLcxid().toString()));
							}else{
								examineoption.addAttribute("LCXID", "");
							}
							if(examineOption.getLcid()!=null&&!"".equals(examineOption.getLcid())){//流程ID
								examineoption.addAttribute("LCID", transToBase64(examineOption.getLcid()));
							}else{
								examineoption.addAttribute("LCID", "");
							}
						}	
					}
				}
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");

		// 上传文件的文件路径
		String outFilePath = "";

		// 将xml文件上传
		FileOutputStream fileout = null;
		XMLWriter outXmlWriter = null;
		String outXmlFile = ""; // 输出的xml文件名

		try {
			String zgyh=tBankDataDemandDao.queryByYhbh(bankCode).getZgyh();
			outXmlFile = grOrdwZHXXCXSQ+zgyh+qqdh+".xml";
			 String outDirPath = uploadPath + File.separator + qqdh + File.separator
						+ bankCode; // 带有银行代码的目录路径;
			File outDir = new File(outDirPath);

			if (!outDir.exists()) {
				//System.out.println("不存在目录，则创建");
				outDir.mkdirs();
			}

			outFilePath = outDirPath + File.separator + outXmlFile;

			// 文件目录路径
			// map.put("outDirPath", outDirPath);
			// xml文件路径
			// map.put("outXmlFilePath", outFilePath);

			// 输出XML文件
			fileout = new FileOutputStream(outFilePath);
			outXmlWriter = new XMLWriter(fileout, format);
			outXmlWriter.write(outDocument);

			fileout.flush();
			outXmlWriter.flush();

			fileout.close();
			outXmlWriter.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileout != null)
					fileout.close();
				if (outXmlWriter != null)
					outXmlWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return outFilePath;
	}


	// 如果参数为null，转为""
	public String isNullToEmpty(String str){
		if(!Commons.checkIsNull(str))
			return str; 	
		else
			return "";
	}
	
	// 将属性值转换为utf-8的base64编码
	public String transToBase64(String prppertyValue){
		String encode = "";
		encode = Base64.encode(prppertyValue, "utf-8");
		return encode;
	}
	
	// 将属性值base64解码为utf-8的字符串
	public String base64ToStr(String prppertyValue){
		String decode = "";
		decode = Base64.decode(prppertyValue, "utf-8");
		return decode;
	}
	/**
	 * 
	    * @描述：协查xml文件打包和发送
	    * @作者：  lixiao       
	    * @时间: 2016-4-6 下午3:27:47     
		* @param qqdh
		* @param projectPath
		* @param userid
	 */
	public void assistGenerateXmlToSend(String qqdh, String projectPath, String userid,String sqlxid,String inoutId,String bankCode){
		String sqlx=Commons.getSqlxDm(sqlxid);
		try{
		// 读取配置文件的名字
		String uploadPath = projectPath + File.separator + "photo"
				+ File.separator + "uploadFiles";
		TBankDataDemand tdd=new TBankDataDemand();
		tdd=tBankDataDemandService.getDemand(qqdh);
		// String xmlDirpath = ""; // 不同银行编号生成多个文件，在此目录（非文件目录）
		if (qqdh != null && !"".equals(qqdh) && !"null".equals(qqdh)) {

			// 20150831 根据新的需求，生成相应的xml文件（最后4个，共10个）
			assistGenerateXmlByQqdh(uploadPath, qqdh, userid,tdd,inoutId,bankCode);
			                                                                                                               
		}
		// 1. 读取生成FMQ的配置文件目录，后面需要获取FMQupload上传目录。
		String FMQConfigPath =Commons.queryPropertiesFilePath("FMQConfigPath"); 
		String provice =Commons.queryPropertiesFilePath("provice");
		PropertyConfig config=configDao.findByProvince(provice, "", "1");
		// FMQ发送zip包，并将zip包文件备份至此目录
		//String FMQZipBackupDir=config.getSendCopyPath();
		String FMQZipBackupDir=PropertiesUtil.assistSendCopyPath;
		//附件目录
		String bankApplyAccesorryPath = PropertiesUtil.bankApplyAccesorryPath;
		File fileDir = new File(uploadPath + File.separator + qqdh);          
		if (fileDir.exists()) {
			File[] files = fileDir.listFiles();//列出这个目录下所有的文件
			if (files.length > 0) {
				for (File file : files) {
					if (file.isDirectory()) {
						//String flag=yhDao.getContentByYhbh(file.getName());
						//根据协查标识符去判断是否属于协查
						if(bankCode.equals(file.getName())){		
							String newDir = file.getAbsolutePath();						
							File f1 = new File(newDir);
							File[] fileList = f1.listFiles();
							// System.out.println(fileList.length);
							if (fileList.length > 0) {
								// 需要打成的zip包文件的名字路径
								String zipFilePath=null;
								String password=null;
								if("01020000".equals(file.getName())||"01030000".equals(file.getName())){
									zipFilePath = newDir + File.separator
											+ file.getName() + qqdh + ".zip";
									password=file.getName() + qqdh;
								}else{
									zipFilePath = newDir + File.separator+provice
											+ file.getName() + qqdh + ".zip";
									password=provice+file.getName()+qqdh;
								}
								File zipFile = new File(zipFilePath);								
								// 20150717使用zip4j方式压缩文件
								ArrayList filelist = new ArrayList();
								for (int i = 0; i < fileList.length; i++) {
									String name=fileList[i].getName().substring(0, 2);
									String name1=fileList[i].getName().substring(0, 2);
									if(!"AS".equals(name)&&name!=null&&!(fileList[i].getName().endsWith(".zip"))){
										filelist.add(fileList[i]);										
									}
								}
								ZipParameters zipParamters = new ZipParameters();
								zipParamters
								.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
								zipParamters
								.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
								zipParamters.setEncryptFiles(true);
								zipParamters
								.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
								zipParamters.setPassword(password);
								
								ZipFile zipFileByPass;
								try {
									zipFileByPass = new ZipFile(zipFilePath);
									zipFileByPass.addFiles(filelist, zipParamters);
								} catch (ZipException e1) {
									e1.printStackTrace();
								}
							}
							File[] xcfileList = f1.listFiles();
							String transferPath = null;
							if(xcfileList.length>0){
								transferPath=PropertiesUtil.zipModdleSendPath;
								String transferFilePath=transferPath+File.separator+qqdh;
								String zipFile=PropertiesUtil.zipFilePath+File.separator+qqdh;
								String zgyh=tBankDataDemandDao.queryByYhbh(file.getName()).getZgyh();
								for (File flie:xcfileList) {
									String name =flie.getName().substring(flie.getName().lastIndexOf("."), flie.getName().length());
									String fname=flie.getName().substring(0, 2);
									if(".zip".equals(name)){
										copyFileToDir(flie.getAbsolutePath(),transferFilePath+File.separator+zgyh);
									}
									if("AS".equals(fname)){
										copyFileToDir(flie.getAbsolutePath(),zipFile+File.separator+zgyh);
									}
								}
							}
							
						}
					}
				}
			}
		}
		/*transferFileToDir(PropertiesUtil.zipModdleSendPath+File.separator+qqdh, qqdh, provice,PropertiesUtil.assistSendPath, sqlx,tdd);
     	// 2. 如果zip文件放到了FMQ上传路径下。审核最后一步，更改申请表状态
		tdd.setSqzt("11"); // 已发送
		tBankDataDemandDao.update(tdd);

		// 3. 加入申请状态跟踪记录
		BkStatusTrack bkst = new BkStatusTrack();
		bkst.setQqdh(qqdh);
		bkst.setSqzt("已发送");
		bkst.setCzsj(new Date());
		bkst.setCzrjh(userid);
		bkStatusTrackDao.save(bkst);*/
	//2017年8月17日 17:51:19 注释 bylixiao 转移到ExamineOptionController  949行
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		
		
	}
	/**
	 * 
	    * @描述：协查根据请求单号生成xml文件
	    * @作者：  lixiao       
	    * @时间: 2016-4-7 上午9:40:18     
		* @param uploadPath生成文件的路径
		* @param qqdh
		* @param userid
		* @param tdd
		* @return
	 */
	private String assistGenerateXmlByQqdh(String uploadPath, String qqdh,
			String userid,TBankDataDemand tdd ,String inoutId,String bankCode) {
		String sqlxid=tdd.getXclx().toString();
		String sqlx=Commons.getSqlxDm(sqlxid);
		//获得文书表数据（TBankAccesorry）
		List<TBankAccesorry> tbalisttemp = new ArrayList<TBankAccesorry>();
		tbalisttemp = tBankAccesorryService.findByPcid(qqdh, "2");//附件类型
		//String province = service.findByQqdh(qqdn);
		String fileDirPath = "";
		List<TBankPersonData> personList=new ArrayList<TBankPersonData>();
		List<TBankDetailData> detail=new ArrayList<TBankDetailData>();
		String propertyName="provice";
		String proPath="config/filepath.properties";
		String provice = ConfigureUtil.getResourceByKey(propertyName,proPath);//本省代码
		// 账号有数据
		Yhdm yhdm = null;
		yhdm = yhDao.getYhmc(bankCode);
		/*if (tbalisttemp != null && tbalisttemp.size() > 0) {
			Yhdm preYhdm = null;
			for (int t = 0; t < tbalisttemp.size(); t++) {
				if(preYhdm != null && yhdm.getYhbh().equals(preYhdm.getYhbh())){
					continue;
				}
				preYhdm = yhdm;
				}
		}*/
				// 账户查询查询
				if(inoutId.equals("102")){
					//TODO 添加附件
					 personList = tBankPersonDataDao.findBySqid(qqdh);
					 detail = tBankDetailDataDao.findByPcid(qqdh);
					//uploadPath
					//String flag=yhDao.getContentByYhbh(bankCode);
					List<TBankAccesorry> tbalisttempXml=tBankAccesorryService.findByPcidXml(qqdh);
					fileDirPath=generateXmlForAssit(sqlx, personList, tdd, detail, tbalisttempXml, uploadPath, bankCode, qqdh,sqlxid);
					
				//}
				if (tdd.getSqlxid() == 101) {
					// 个人涉案对象信息，取得 person表中的数据。
					List<TBankPersonData> grtbpdlist = new ArrayList<TBankPersonData>();
					grtbpdlist = tBankPersonDataDao.findPersonByQqdh(qqdh, bankCode, "个人");
					if(grtbpdlist!=null&&grtbpdlist.size()>0)
					{
						// 生成个人账户信息查询的xml文件。
						fileDirPath = generateXmlForPerson("SS01",grtbpdlist, tdd, uploadPath,bankCode, qqdh);
						// 申请任务记录
						List grzhzzhmlist = new ArrayList();
						for (TBankPersonData grzh : grtbpdlist) {
							String grzhzzhm = grzh.getZjhm()+"!"+grzh.getRwlsh();
							grzhzzhmlist.add(grzhzzhm);
						}
						saveTask(qqdh, grzhzzhmlist, bankCode, userid,"SS01");
						// 2015-09-21
						addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送个人账户信息查询",grtbpdlist.size(), "SS01");
					}
					// 单位涉案对象信息， 取得 person表中的数据。
					List<TBankPersonData> dwtbpdlist = new ArrayList<TBankPersonData>();
					dwtbpdlist = tBankPersonDataDao.findPersonByQqdh(qqdh, bankCode, "单位");
					if(dwtbpdlist!=null&&dwtbpdlist.size()>0)
					{
						// 生成个人账户信息查询的xml文件。
						fileDirPath = generateXmlForPerson("SS02",dwtbpdlist, tdd, uploadPath,bankCode, qqdh);
						// 申请任务记录
						List dwzhzzhmlist = new ArrayList();
						for (TBankPersonData grzh : dwtbpdlist) {
							String grzhzzhm = grzh.getZjhm()+"!"+grzh.getRwlsh();;
							dwzhzzhmlist.add(grzhzzhm);
						}
						saveTask(qqdh, dwzhzzhmlist, bankCode, userid,"SS02");
						// 2015-09-21
						addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位账户信息查询",dwtbpdlist.size(), "SS02");
					}
					//个人账户信息
					List<TBankDetailData> grdetaillist = new ArrayList<TBankDetailData>();
					grdetaillist = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'个人','未知'");
					if(grdetaillist!=null&&grdetaillist.size()>0)
					{
						// 生成个人账号明细查询的xml文件。
						fileDirPath = generateXmlForDetail("SS05",grdetaillist, tdd, uploadPath,bankCode, qqdh);
						
						// 申请任务记录
						List grmxzzhmlist = new ArrayList();
						for (TBankDetailData grmx : grdetaillist) {
							String grmxzzhm = grmx.getZh()+"!"+grmx.getRwlsh();
							grmxzzhmlist.add(grmxzzhm);
						}
						saveTask(qqdh, grmxzzhmlist, bankCode, userid,"SS05");
					
						addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送个人开户信息查询",grdetaillist.size(), "SS05");
					}
					
					//单位账户信息
					List<TBankDetailData> dwdetaillist = new ArrayList<TBankDetailData>();
					dwdetaillist = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'单位','未知'");
					if(dwdetaillist!=null&&dwdetaillist.size()>0)
					{
						// 生成单位账号明细查询的xml文件。
						fileDirPath = generateXmlForDetail("SS06",dwdetaillist, tdd, uploadPath,bankCode, qqdh);

						// 申请任务记录
						List dwmxzzhmlist = new ArrayList();
						for (TBankDetailData dwmx : dwdetaillist) {
							String dwmxzzhm = dwmx.getZjhm()+"!"+dwmx.getRwlsh();
							dwmxzzhmlist.add(dwmxzzhm);
						}
						saveTask(qqdh, dwmxzzhmlist, bankCode, userid,"SS06");
						// 2015-09-21

						addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位开户信息查询",dwdetaillist.size(), "SS06");
					}	
				}
				else if("204".equals(tdd.getXclx()) || "205".equals(tdd.getXclx()) || "206".equals(tdd.getXclx()))
				{
					// 204：协查冻结；204：续冻协查；206：协查解冻
					//个人账户信息
					List<TBankDetailData> grdetaillist = new ArrayList<TBankDetailData>();
					String bankcode=bankCode;
					grdetaillist = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'个人','未知'");
					if(grdetaillist!=null&&grdetaillist.size()>0)
					{
						// 生成个人协助冻结查询的xml文件。
						fileDirPath = generateXmlForFreeze("SS17",grdetaillist, tdd, uploadPath,bankCode, qqdh);
					
						//任务管理
						List grxzdjlist = new ArrayList();
						for(TBankDetailData grxzdj:grdetaillist){
							String grxz=grxzdj.getZh()+"!"+grxzdj.getRwlsh();
							grxzdjlist.add(grxz);
						}
						saveTask(qqdh, grxzdjlist, bankCode, userid,"SS17");
											
						addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送个人协助冻结查询",grdetaillist.size(), "SS17");
					}
					//单位账户信息
					List<TBankDetailData> danweiDetailList = new ArrayList<TBankDetailData>();
					danweiDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'单位','未知'");
					if(danweiDetailList!=null&&danweiDetailList.size()>0)
					{
						// 生成单位协助冻结查询的xml文件。
						fileDirPath = generateXmlForFreeze("SS18",danweiDetailList, tdd, uploadPath,bankCode, qqdh);
					
						//任务管理
						List dwxzdjlist = new ArrayList();
						for(TBankDetailData dwxzdj:danweiDetailList){
							String dwxz=dwxzdj.getZh()+"!"+dwxzdj.getRwlsh();
							dwxzdjlist.add(dwxz);
						}
						saveTask(qqdh, dwxzdjlist, bankCode, userid,"SS18");
						
						addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位协助冻结查询",danweiDetailList.size(), "SS18");
					}
				} else if ("304".equals(tdd.getXclx()) || "305".equals(tdd.getXclx()) || "306".equals(tdd.getXclx())) {
					// 304：布控（动态）
					//个人账户信息
					List<TBankDetailData> gerenDetailList = new ArrayList<TBankDetailData>();
					gerenDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'个人','未知'");
					if(gerenDetailList!=null&&gerenDetailList.size()>0)
					{
						// 生成个人协助布控查询的xml文件。
						fileDirPath = generateXmlForControl("SS11",	gerenDetailList, tdd, uploadPath,bankCode, qqdh);
					
						//任务管理
						List grxzbkdjlist = new ArrayList();
						for(TBankDetailData grxzbkcx:gerenDetailList){
							String grxzbk=grxzbkcx.getZh()+"!"+grxzbkcx.getRwlsh();
							grxzbkdjlist.add(grxzbk);
						}
						if(grxzbkdjlist != null && grxzbkdjlist.size() > 0){
							saveTask(qqdh, grxzbkdjlist, bankCode, userid,"SS11");
						}
						
						addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送个人协助冻结查询",gerenDetailList.size(), "SS11");
					}
					//单位账户信息
					List<TBankDetailData> dwDetailList = new ArrayList<TBankDetailData>();
					dwDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'单位','未知'");
					if(dwDetailList!=null&&dwDetailList.size()>0)
					{
					// 生成单位协助布控查询的xml文件。
					fileDirPath = generateXmlForControl("SS12",dwDetailList, tdd, uploadPath,bankCode, qqdh);
						
						//任务管理
						List dwxzbkdjlist = new ArrayList();
						for(TBankDetailData dwxzbkcx:dwDetailList){
							String dwxzbk=dwxzbkcx.getZh()+"!"+dwxzbkcx.getRwlsh();
							dwxzbkdjlist.add(dwxzbk);
						}
						saveTask(qqdh, dwxzbkdjlist, bankCode, userid,"SS12");
						
						addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位协助冻结查询",dwDetailList.size(), "SS12");
					}
				} else if ("403".equals(tdd.getXclx()) || "404".equals(tdd.getXclx())) {
					// 紧急止付
					List<TBankDetailData> grDetailList = new ArrayList<TBankDetailData>();
					grDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'个人','未知'");
					if(grDetailList!=null&&grDetailList.size()>0)
					{
					// 生成账号明细查询的xml文件。
					// 生成个人协助布控查询的xml文件。
					fileDirPath = generateXmlForStopPay("SS21",grDetailList, tdd, uploadPath, bankCode, qqdh);
						
						//任务管理
						List grbkmxdjlist = new ArrayList();
						for(TBankDetailData grbkmxcx:grDetailList){
							String grbkmx=grbkmxcx.getZh()+"!"+grbkmxcx.getRwlsh();
							grbkmxdjlist.add(grbkmx);
						}
						saveTask(qqdh, grbkmxdjlist,  bankCode, userid,"SS21");
						
						addApplyStatus(qqdh, yhdm.getYhmc() ,  bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc()  + "发送个人紧急止付查询",grDetailList.size(), "SS21");
					}
					//单位账户信息
					List<TBankDetailData> dwzfDetailList = new ArrayList<TBankDetailData>();
					dwzfDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, bankCode, "'单位','未知'");
					if(dwzfDetailList!=null&&dwzfDetailList.size()>0)
					{
					// 生成单位协助布控查询的xml文件。
					fileDirPath = generateXmlForStopPay("SS22",dwzfDetailList, tdd, uploadPath,bankCode, qqdh);
						
						//任务管理
						List dwbkmxdjlist = new ArrayList();
						for(TBankDetailData dwbkmxcx:dwzfDetailList){
							String dwbkmx=dwbkmxcx.getZh()+"!"+dwbkmxcx.getRwlsh();
							dwbkmxdjlist.add(dwbkmx);
						}
						saveTask(qqdh, dwbkmxdjlist, bankCode, userid,"SS22");
						
						addApplyStatus(qqdh, yhdm.getYhmc(), bankCode, fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位紧急止付查询",dwzfDetailList.size(), "SS22");
					}
				}
//*****************生成协查申请的xml文件*****************
				else if(tdd.getInoutid() == 102){}
				
				//添加附件
				applySendService.saveJgzAndWs(qqdh, uploadPath ,bankCode);
				List<UserPhoto> photos = null;
				// 查询人警官证
				photos = userPhotoDao.findByUserId(tdd.getCbr1());
				applySendService.saveJgz(photos, uploadPath,bankCode,qqdh,"LI26"+bankCode+qqdh);
				// 协办人警官证
				photos = userPhotoDao.findByUserId(tdd.getCbr2());
				applySendService.saveJgz(photos, uploadPath,bankCode,qqdh,"LI27"+bankCode+qqdh);
			}
				

		else
		{
			List<TBankDetailData> jjzfAccountlisttemp = tBankDetailDataDao.findByPcid(qqdh);
			Yhdm preYhdm = null;
			Yhdm bank = null;
			for (int t = 0; t < jjzfAccountlisttemp.size(); t++) {
				yhdm = yhDao.getYhmc(jjzfAccountlisttemp.get(t).getBankcode());
				if(preYhdm != null && yhdm.getYhbh().equals(preYhdm.getYhbh())){
					continue;
				}
				preYhdm = yhdm;
				if ("403".equals(tdd.getXclx()) || "404".equals(tdd.getXclx())) {
					// 紧急止付
					List<TBankDetailData> grDetailList = new ArrayList<TBankDetailData>();
					grDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, jjzfAccountlisttemp.get(t).getBankcode(), "'个人','未知'");
					if(grDetailList!=null&&grDetailList.size()>0)
					{
					// 生成账号明细查询的xml文件。
					// 生成个人协助布控查询的xml文件。
					fileDirPath = generateXmlForStopPay("SS21",grDetailList, tdd, uploadPath, jjzfAccountlisttemp.get(t).getBankcode(), qqdh);
						
						//任务管理
						List grbkmxdjlist = new ArrayList();
						for(TBankDetailData grbkmxcx:grDetailList){
							String grbkmx=grbkmxcx.getZh()+"!"+grbkmxcx.getRwlsh();
							grbkmxdjlist.add(grbkmx);
						}
						saveTask(qqdh, grbkmxdjlist,  jjzfAccountlisttemp.get(t).getBankcode(), userid,"SS21");
						
						addApplyStatus(qqdh, yhdm.getYhmc() ,  jjzfAccountlisttemp.get(t).getBankcode(), fileDirPath,"申请发送：向" + yhdm.getYhmc()  + "发送个人紧急止付查询",grDetailList.size(), "SS21");
					}
					//单位账户信息
					List<TBankDetailData> dwzfDetailList = new ArrayList<TBankDetailData>();
					dwzfDetailList = tBankDetailDataDao.getDetailByQqdh(qqdh, jjzfAccountlisttemp.get(t).getBankcode(), "'单位','未知'");
					if(dwzfDetailList!=null&&dwzfDetailList.size()>0)
					{
					// 生成单位协助布控查询的xml文件。
					fileDirPath = generateXmlForStopPay("SS22",dwzfDetailList, tdd, uploadPath,jjzfAccountlisttemp.get(t).getBankcode(), qqdh);
						
						//任务管理
						List dwbkmxdjlist = new ArrayList();
						for(TBankDetailData dwbkmxcx:dwzfDetailList){
							String dwbkmx=dwbkmxcx.getZh()+"!"+dwbkmxcx.getRwlsh();
							dwbkmxdjlist.add(dwbkmx);
						}
						saveTask(qqdh, dwbkmxdjlist, jjzfAccountlisttemp.get(t).getBankcode(), userid,"SS22");
						
						addApplyStatus(qqdh, yhdm.getYhmc(), jjzfAccountlisttemp.get(t).getBankcode(), fileDirPath,"申请发送：向" + yhdm.getYhmc() + "发送单位紧急止付查询",dwzfDetailList.size(), "SS22");
					}
				}
				//添加附件
				//applySendService.saveJgzAndWs(qqdh, uploadPath ,bankCode);
			}
		}
		return "";
	}
	
	/**
	 * 託管銀行文件處理
	 * @param transferPath 中轉處理目錄
	 * @param filePath 需要轉移文件目錄
	 * @param yhbh 銀行編號
	 * @param bankCodeDirPath fmq發送目錄
	 */
	public void transferFileToDir(String transferPath,String qqdh,String province,String bankCodeDirPath,String sqlx,TBankDataDemand tdd){
		String transferFilePath=PropertiesUtil.zipModdleSendPath;
		String FMQZipBackupDir = PropertiesUtil.FMQZipBackupDir;
		//將文件轉移到中轉目錄	
		File file=new File(transferPath);
		String zipPath=PropertiesUtil.zipFilePath;
		File[] files=file.listFiles();
		//遍歷根目錄下所有目錄
		for (File fileCata : files) {
			if(fileCata.isDirectory()){
				File fileChild=new File(fileCata.getAbsolutePath());				
				File[] fileChilds=fileChild.listFiles();				
				if(fileChilds.length>1){
					ArrayList filelist = new ArrayList();
					File filePath=new File(zipPath+File.separator+qqdh+File.separator+fileCata.getName());
					if(!filePath.exists()){
						filePath.mkdirs();
					}
					String zipFilePath=zipPath+File.separator+qqdh+File.separator+fileCata.getName()+File.separator+province+fileCata.getName()+qqdh+".zip";
					for (File file2 : fileChilds) {
						String fname=file2.getName().substring(0, 2);
						if(!"AS".equals(fname)){
							filelist.add(file2);							
						}
					}
					File zipFile=new File(zipFilePath);
					ZipParameters zipParamters = new ZipParameters();
					zipParamters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
					zipParamters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
					zipParamters.setEncryptFiles(true);
					zipParamters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
					zipParamters.setPassword(province+fileCata.getName()+qqdh);
					ZipFile zipFileByPass;
					try {
						zipFileByPass = new ZipFile(zipFilePath);
						zipFileByPass.addFiles(filelist, zipParamters);
					} catch (ZipException e1) {
						e1.printStackTrace();
					}
					if(zipFile.exists()){
						String bankCode=fileCata.getName();
						createAsFile(bankCode,qqdh,zipPath+File.separator+qqdh+File.separator+fileCata.getName(),sqlx,tdd);
						sendAsFile(zipPath+File.separator+qqdh+File.separator+fileCata.getName(), bankCodeDirPath,tdd,fileCata.getName(),qqdh);
					}
				}else if(fileChilds.length==1){
					for (File file2 : fileChilds) {
						String name=file2.getName().substring(file2.getName().lastIndexOf("."), file2.getName().length());
						if(".zip".equals(name)){
							String bankCode=file2.getName().substring(0, 8);
							if("01020000".equals(bankCode)||"01030000".equals(bankCode)){
								bankCode=bankCode;
							}else{
								bankCode=file2.getName().substring(6, 23);
							}
							if(bankCode.equals(fileCata.getName())){
								//非託管銀行,直接發走；
								copyFileToDir(file2.getAbsolutePath(), zipPath+File.separator+qqdh+File.separator+fileCata.getName());
								createAsFile(fileCata.getName(),qqdh,zipPath+File.separator+qqdh+File.separator+fileCata.getName(),sqlx,tdd);
								sendAsFile(zipPath+File.separator+qqdh+File.separator+fileCata.getName(), bankCodeDirPath,tdd,fileCata.getName(),qqdh);
							}else{
								//託管銀行，文件再打包一層，原來銀行代碼替換成主管銀行代碼
								ArrayList filelist = new ArrayList();
								filelist.add(file2);
								String zipFilePath=zipPath+File.separator+qqdh+File.separator+fileCata.getName()+File.separator+province+fileCata.getName()+qqdh+".zip";
								File zipFile = new File(zipFilePath);
								ZipParameters zipParamters = new ZipParameters();
								zipParamters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
								zipParamters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
								zipParamters.setEncryptFiles(true);
								zipParamters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
								zipParamters.setPassword(province+fileCata.getName()+qqdh);
								ZipFile zipFileByPass;
								try {
									zipFileByPass = new ZipFile(zipFilePath);
									zipFileByPass.addFiles(filelist, zipParamters);
								} catch (ZipException e1) {
									e1.printStackTrace();
								}
								file2.delete();
								if(zipFile.exists()){
									createAsFile(fileCata.getName(),qqdh,zipPath+File.separator+qqdh+File.separator+fileCata.getName(),sqlx,tdd);
									sendAsFile(zipPath+File.separator+qqdh+File.separator+fileCata.getName(), bankCodeDirPath,tdd,fileCata.getName(),qqdh);
								}
							}
							
						}
				}
				
				
			}	
		}
	}
		//清除zip目录和transfer目录下文件
		File zipFile=new File(zipPath);
		cleanFile(zipFile);
		mkdir(zipFile);
		File transferFile=new File(transferFilePath);
		cleanFile(transferFile);
		mkdir(transferFile);
}	
	/**
	 * 递归清除所有文件
	 * @param filePath
	 */
	public  boolean cleanFile(File filePath){
		if(filePath.isDirectory()){
			String[] filePaths=filePath.list();
			for (String path:filePaths) {
				boolean success=cleanFile(new File(filePath,path));
				if(!success){
					return false;
				}
			}
		}
		return filePath.delete();
	}
	
	/**
	 * 建立根目录
	 * @param filePath
	 */
	public  void mkdir(File filePath){
		if(!filePath.exists()){
			filePath.mkdirs();
		}
	}
	
	/**
	 * 生成协查zip包
	 * @param bankCode
	 * @param qqdh
	 * @param filepath
	 * @param sqlx
	 * @param tdd
	 */
	public void createAsFile(String bankCode,String qqdh,String filepath,String sqlx,TBankDataDemand tdd){
			File files=new File(filepath);
			File[] xcfileList=files.listFiles();
			String newDir=filepath;
			String bankApplyAccesorryPath=PropertiesUtil.bankApplyAccesorryPath;
			if(xcfileList.length>0){
			String zipFilePath = newDir + File.separator
					+sqlx+bankCode + qqdh + ".zip";
			ArrayList xcfilelist = new ArrayList();
			for (int i = 0; i < xcfileList.length; i++) {
				xcfilelist.add(xcfileList[i]);
			}
			if(tdd.getXclx()!=null&&!("").equals(tdd.getXclx())) {
				//	获取附件文件
				File[]  asFileList =  new File(bankApplyAccesorryPath + qqdh).listFiles();
				for(File as : asFileList) {
					xcfilelist.add(as);
				}
			} 
			ZipParameters xcZipParamters = new ZipParameters();
			xcZipParamters
			.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			xcZipParamters
			.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			xcZipParamters.setEncryptFiles(true);
			xcZipParamters
			.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
			xcZipParamters.setPassword(bankCode+qqdh);
			ZipFile xcZipFileByPass;
			try {
				xcZipFileByPass = new ZipFile(zipFilePath);
				xcZipFileByPass.addFiles(xcfilelist, xcZipParamters);
			} catch (ZipException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 将生成的zip文件移动到发送通道
	 * @param filepath
	 * @param bankCodeDirPath
	 * @param tdd
	 * @param yhbh
	 */
	public void sendAsFile(String filepath,String bankCodeDirPath,TBankDataDemand tdd,String yhbh,String qqdh){
		String FMQZipBackupDir=PropertiesUtil.assistSendCopyPath;
		File f1=new File(filepath);
		File[] xcfileList1= f1.listFiles();
		String currentZipFilePath = null;
		for (File file2 : xcfileList1) {
			currentZipFilePath = file2.getName();
			String fname=currentZipFilePath.substring(0, 2);
			String lastName=currentZipFilePath.substring(currentZipFilePath.lastIndexOf("."), currentZipFilePath.length());
			if(("AS".equals(fname)&&".zip".equals(lastName))){
				// 按bankcode查找对应银行路径
				//String bankCodeDirPath = null;
				bankCodeDirPath=PropertiesUtil.assistSendPath;
				if (bankCodeDirPath != null&& !"".equals(bankCodeDirPath)) {
					//如果存在预设时间发送
					if(tdd.getPresetTime() != null){
						bankCodeDirPath=PropertiesUtil.ZipWaitPath;
					}
					copyFileToDir(file2.getAbsolutePath(),bankCodeDirPath+File.separator);
					// 复制zip包到备份目录
					if (FMQZipBackupDir != null && !"".equals(FMQZipBackupDir)) {
						String yhmc=yhDao.getContentByYhbh(yhbh);
						copyFileToDir(file2.getAbsolutePath(),FMQZipBackupDir+File.separator+yhmc+File.separator+qqdh);
						file2.delete();
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		String name="AS101C003H1011101010015000999120170511133137.zip";
		String name1=name.substring(name.lastIndexOf("."), name.length());
		System.out.println(name1);
	}
}
