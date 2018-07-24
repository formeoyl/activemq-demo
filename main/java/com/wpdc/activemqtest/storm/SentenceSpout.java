package com.wpdc.activemqtest.storm;

import java.util.Map;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import javax.jms.*;

/**
 * 向后端发射tuple数据流
 * @author soul
 *
 */
public class SentenceSpout extends BaseRichSpout {

    //BaseRichSpout是ISpout接口和IComponent接口的简单实现，接口对用不到的方法提供了默认的实现

    private  static final long serialVersionUID=1L;
    private TopologyContext context;
    private SpoutOutputCollector collector;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;

//    private SpoutOutputCollector collector;

    private String[] sentences = {
            "my name is soul",
            "im a boy",
            "i have a dog",
            "my dog has fleas",
            "my girl friend is beautiful"
    };

    private int index=0;

    /**
     * open()方法中是ISpout接口中定义，在Spout组件初始化时被调用。
     * open()接受三个参数:一个包含Storm配置的Map,一个TopologyContext对象，提供了topology中组件的信息,SpoutOutputCollector对象提供发射tuple的方法。
     * 在这个例子中,我们不需要执行初始化,只是简单的存储在一个SpoutOutputCollector实例变量。
     */
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        // TODO Auto-generated method stub
//        this.collector = collector;
        this.context=topologyContext;
        this.collector=spoutOutputCollector;
        this.connectionFactory=new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://192.168.71.211:61616");
        try {
            connection=connectionFactory.createConnection();
            connection.start();
            session=connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            destination=session.createQueue("queue-1");
            consumer=session.createConsumer(destination);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * nextTuple()方法是任何Spout实现的核心。
     * Storm调用这个方法，向输出的collector发出tuple。
     * 在这里,我们只是发出当前索引的句子，并增加该索引准备发射下一个句子。
     */
    public void nextTuple() {
        //collector.emit(new Values("hello world this is a test"));

        // TODO Auto-generated method stub
//        this.collector.emit(new Values(sentences[index]));
//        index++;
//        if (index>=sentences.length) {
//            index=0;
//        }
//        Utils.sleep(1);
        try {
            TextMessage message= (TextMessage) consumer.receive(100000);
            this.collector.emit(new Values(message.getText()));
        }catch (Exception e){

        }
    }

    /**
     * declareOutputFields是在IComponent接口中定义的，所有Storm的组件（spout和bolt）都必须实现这个接口
     * 用于告诉Storm流组件将会发出那些数据流，每个流的tuple将包含的字段
     */
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub

        declarer.declare(new Fields("sentence"));//告诉组件发出数据流包含sentence字段

    }

}
