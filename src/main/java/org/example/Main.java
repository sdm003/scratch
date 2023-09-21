package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.engine.ScratchGame;
import org.example.entity.Config;
import org.example.result.Result;

import java.io.File;
import java.io.IOException;

public class Main {

    private static String configFilePath;
    private static Integer bettingAmount;

    public static void main(String[] args) {
        extractAndSetArgs(args);

        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            Config config = mapper.readValue(new File(configFilePath), Config.class);

            ScratchGame game = new ScratchGame(config);
            Result result = game.play(bettingAmount);

            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));

        } catch (IOException e) {
            System.out.println("Error reading config.json: " + e.getMessage());
        }
    }

    private static void extractAndSetArgs(String[] args){

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--config":
                    configFilePath = args[++i];
                    break;
                case "--betting-amount":
                    bettingAmount = Integer.parseInt(args[++i]);
                    break;
                default:
                    System.out.println("Unknown option: " + args[i]);
                    return;
            }
        }

    }

}
