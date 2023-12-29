package src.model;

import src.Channels.ChannelStrategy;
import src.model.Messages.MessageTemplate;

public class Notification {
    private ChannelStrategy channel;
    private MessageTemplate message;

      public MessageTemplate getMessage() {
        return message;
    }
    public void setMessage(MessageTemplate message) {
        this.message = message;
    }
    public ChannelStrategy getChannel() {
        return channel;
    }
    public void setChannel(ChannelStrategy channel) {
        this.channel = channel;
    }
    
    
}
