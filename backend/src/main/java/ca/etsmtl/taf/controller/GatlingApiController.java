package ca.etsmtl.taf.controller;

import ca.etsmtl.taf.entity.GatlingRequest;
import ca.etsmtl.taf.provider.GatlingJarPathProvider;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/gatling")
public class GatlingApiController {
    @RequestMapping(value = "/runSimulation", method = RequestMethod.POST)
    public String runSimulation(@RequestBody GatlingRequest gatlingRequest) {
        try {
            String gatlingJarPath = new GatlingJarPathProvider().getGatlingJarPath();

            String testRequest = "{\\\"baseUrl\\\":\\\""+gatlingRequest.getBaseUrl()+"\\\",\\\"scenarioName\\\":\\\""+gatlingRequest.getScenarioName()+"\\\",\\\"requestName\\\":\\\""+gatlingRequest.getRequestName()+"\\\",\\\"uri\\\":\\\""+gatlingRequest.getUri()+"\\\",\\\"requestBody\\\":\\\""+gatlingRequest.getRequestBody()+"\\\",\\\"methodType\\\":\\\""+gatlingRequest.getMethodType()+"\\\"}";
            //Construire une liste d'arguments de ligne de commande à transmettre à Gatling
            List<String> commandArgs = new ArrayList<>();
            commandArgs.add("java");
            commandArgs.add("-jar");
            commandArgs.add(gatlingJarPath);
            commandArgs.add("-DrequestJson=" + testRequest);

            // Exécuter la simulation Gatling en tant que processus distinct
            ProcessBuilder processBuilder = new ProcessBuilder(commandArgs);
            Process process = processBuilder.start();
            // Lire le résultat du processus
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append('\n');
            }

            int exitCode = process.waitFor();
            return "Exit Code: " + exitCode + "\nOutput:\n" + output.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}