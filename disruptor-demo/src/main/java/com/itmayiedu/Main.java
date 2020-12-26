package com.itmayiedu;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class Main {

	public static void main(String[] args) {

		// 创建缓冲池
		ExecutorService executor = Executors.newCachedThreadPool();
		// 创建工厂
		LongEventFactory factory = new LongEventFactory();
		// 创建bufferSize ,也就是RingBuffer大小，必须是2的N次方
		int ringBufferSize = 1024 * 1024; //
		// 创建disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executor,
				ProducerType.SINGLE, new YieldingWaitStrategy());
		disruptor.handleEventsWith(new LongEventHandler());
		disruptor.start();
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

		LongEventProducer producer = new LongEventProducer(ringBuffer);

		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		for (int i = 0; i < 100; i++) {
			byteBuffer.putLong(0, i);
			producer.onData(byteBuffer);
		}
		disruptor.shutdown();
		executor.shutdown();
	}

}
