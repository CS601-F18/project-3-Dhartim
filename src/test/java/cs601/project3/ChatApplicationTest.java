package cs601.project3;

import static org.junit.Assert.*;

import org.junit.Test;
import cs601.project3.ConfigurationBean;

public class ChatApplicationTest {
	String[] args = new String[]{"configuration.json"};

	@Test
	public void testGetTermScenario1() 
	{
		ReadConfigurationFile readconfigfile = new ReadConfigurationFile();
		ConfigurationBean configbean = readconfigfile.readJsonFile(args[0]);
		//SlackBot slackbot = new SlackBot(args[0]);
		ChatHandler handle = new ChatHandler();
		String parameter[] = {"message", "hello"};
		//boolean status = slackbot.sendMsgToSlack("message=");
		//assertFalse((handle.splitMessage(parameter, parameter[0], parameter[1])));
	}
}