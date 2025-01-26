package com.telegram.WeatherBot.service.handler;

import com.telegram.WeatherBot.data.CommandData;
import com.telegram.WeatherBot.service.manager.ForecastManager;
import com.telegram.WeatherBot.service.manager.MainManager;
import com.telegram.WeatherBot.telegram.Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandler {

    MainManager mainManager;
    ForecastManager forecastManager;
    public BotApiMethod<?> answer(Message message, Bot bot) {

        String command = message.getText().substring(1);
        CommandData commandData;
        try {
            commandData = CommandData.valueOf(command);
        } catch (Exception e) {
            log.warn("Unsupported command was received: " + command);
            return unknownCommand(message.getChatId(), command);
        }
        switch (commandData) {
            case start -> {
                return mainManager.answerCommand(message, bot);
            }
            case help -> {
                return showListOfCommands(message.getChatId());
            }
            case forecast -> {
                return forecastManager.answerCommand(message, bot);
            }
        }
        throw new UnsupportedOperationException();
    }

    private BotApiMethod<?> showListOfCommands(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(
                        """
                                🌞 /start - начни взаимодействовать с ботом
                                🌗 /help - перечень комманд
                                🌚 /forecast - главная страница прогноза
                                """
                )
                .build();
    }

    private BotApiMethod<?> unknownCommand(Long chatId, String command) {
        return SendMessage.builder()
                .text("Я не знаю комманды {" + command + "}\n\n Ознакомьтесь с перечнем комманд по команде ниже: \n /help")
                .chatId(chatId)
                .build();
    }

}
