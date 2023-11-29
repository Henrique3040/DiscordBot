package org.example;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public class Bot {
    public static void main(String[] args) {

        DiscordClient client = DiscordClient.create("");

        // This part of the code determines which messages in the chat to listen to
        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) ->
                gateway.on(MessageCreateEvent.class, event -> {
                    Message message = event.getMessage();

                    if (message.getContent().equalsIgnoreCase("!ping")) {
                        return message.getChannel()
                                .flatMap(channel -> channel.createMessage("pong!"));
                    }

                    return Mono.empty();
                }));

        login.block();

    }
}
