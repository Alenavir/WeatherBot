package com.telegram.WeatherBot.service.manager;

import com.telegram.WeatherBot.service.factory.KeyBoardFactory;
import com.telegram.WeatherBot.telegram.Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

import static com.telegram.WeatherBot.data.QueryData.fc;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainManager {

    KeyBoardFactory keyboardFactory;

    public BotApiMethod<?> answerCommand(Message message, Bot bot) {
        return SendMessage.builder()
                .text(
                        """
                                ☀️Приветствую в боте прогноза погоды!
                                                                
                                ⭐️С помощью этого бота вы можете:  
                                                                                      
                                👀 Смотреть прогноз погоды на 7 дней            
                                👀 Смотреть прогноз погоды на день (Информацию о каждом часе дня)
                                                                
                                Нажмите сюда /help, чтобы посмотреть перечень команд
                                                                
                                Нажав на кнопку снизу, вы переместитесь на страницу прогноза, приятного пользования!
                                """
                )
                .chatId(message.getChatId())
                .replyMarkup(
                        keyboardFactory.getInlineKeyboard(
                                List.of("⛈ Прогноз ⛈"),
                                List.of(1),
                                List.of(fc.name())
                        )
                )
                .build();
    }

    public BotApiMethod<?> answerQuery(CallbackQuery query, String[] data, Bot bot) {
        return EditMessageText.builder()
                .text(
                        """
                                ☀️Приветствую в боте прогноза погоды!
                                                                
                                ⭐️С помощью этого бота вы можете:  
                                                                                      
                                👀 Смотреть прогноз погоды на 7 дней            
                                👀 Смотреть прогноз погоды на день (Информацию о каждом часе дня)
                                                                
                                Нажмите сюда /help, чтобы посмотреть перечень команд
                                                                
                                Нажав на кнопку снизу, вы переместитесь на страницу прогноза, приятного пользования!
                                """
                )
                .chatId(query.getMessage().getChatId())
                .messageId(query.getMessage().getMessageId())
                .replyMarkup(
                        keyboardFactory.getInlineKeyboard(
                                List.of("⛈ Прогноз ⛈"),
                                List.of(1),
                                List.of(fc.name())
                        )
                )
                .build();
    }

}
