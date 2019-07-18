package com.game.controller;

import java.awt.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value = "/")
public class GameController {
	@RequestMapping(value = "/gameMain", method=RequestMethod.GET)
	public void gameMain(HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.info("interface gameMain start up...");
		SwingUtilities.invokeLater(() -> {
		      JFrame f = new JFrame();
		      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      f.setTitle("2048");
		      f.setResizable(true);
		      f.add(new Game2048(), BorderLayout.CENTER);
		      f.pack();
		      f.setLocationRelativeTo(null);
		      f.setVisible(true);
		    });
		log.info("interface gameMain start up end...");
	}
	
	
}
