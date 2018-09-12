package com.maxlong.disruptor.eventProWithTrans;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.maxlong.disruptor.event.LongEvent;

public class LongEventProducerWithTranslator {

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            (event, sequence, bb) -> event.setValue(bb.getLong(0));
            
    private final RingBuffer<LongEvent> ringBuffer;
    
    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) { 
        this.ringBuffer = ringBuffer; 
    } 
}
 