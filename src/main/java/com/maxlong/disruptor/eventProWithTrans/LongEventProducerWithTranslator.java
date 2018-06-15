package com.maxlong.disruptor.eventProWithTrans;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.maxlong.disruptor.event.LongEvent;

public class LongEventProducerWithTranslator {

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = 
            new EventTranslatorOneArg<LongEvent, ByteBuffer>() { 
                public void translateTo(LongEvent event, long sequence, ByteBuffer bb) { 
                    event.setValue(bb.getLong(0)); 
                } 
            };
            
    private final RingBuffer<LongEvent> ringBuffer;
    
    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) { 
        this.ringBuffer = ringBuffer; 
    } 
}
 