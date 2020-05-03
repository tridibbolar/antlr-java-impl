package com.tcg.rr.parser.iata;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import com.example.PnlAdlLexer;
import com.example.PnlAdlParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Hello world!
 *
 */
public class App {
	
	private static final String input = "PNLKL774/06JUN ZRH PART1-FRA03F-PAD15\r\nENDPART1\r\nENDPNL\r\n\r\n";
	private static final Gson PRETTY_PRINT_GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Gson GSON = new Gson();
	private static PnlAdlLexer lexer;

	public static String toJson(ParseTree tree) {
		return toJson(tree, true);
	}

	public static String toJson(ParseTree tree, boolean prettyPrint) {
		return prettyPrint ? PRETTY_PRINT_GSON.toJson(toMap(tree)) : GSON.toJson(toMap(tree));
	}

	public static Map<String, Object> toMap(ParseTree tree) {
		Map<String, Object> map = new LinkedHashMap();
		traverse(tree, map);
		return map;
	}

	public static void traverse(ParseTree tree, Map<String, Object> map) {
		
		
		
		if (tree instanceof TerminalNodeImpl) {
			Token token = ((TerminalNodeImpl) tree).getSymbol();
			map.put("field",lexer.getVocabulary().getSymbolicName(token.getType()));
			map.put("type", token.getType());
			map.put("text", token.getText());
		} else {
			List<Map<String, Object>> children = new ArrayList();
			String name = tree.getClass().getSimpleName().replaceAll("Context$", "");
			map.put(Character.toLowerCase(name.charAt(0)) + name.substring(1), children);

			for (int i = 0; i < tree.getChildCount(); i++) {
				Map<String, Object> nested = new LinkedHashMap<String, Object>();
				children.add(nested);
				traverse(tree.getChild(i), nested);
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World!");

		lexer = new PnlAdlLexer(CharStreams.fromString(input));

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PnlAdlParser parser = new PnlAdlParser(tokens);
		ParseTree tree = parser.list().getChild(0);
		System.out.println(toJson(tree));
		/*
		 * System.out.println("Text: " + tree.getText() + ", Child count: " +
		 * tree.getChildCount() + ", Payload: " + tree.getPayload().toString()); // for
		 * (tree.getChildCount() ) IntStream.range(0, tree.getChildCount()).forEach( nbr
		 * ->{ System.out.println(tree.getChild(nbr).getChildCount());
		 * System.out.println(tree.getChild(nbr).getText()); });
		 */

		/*ParseTreeWalker walker = new ParseTreeWalker();
		CustomMethodListener listener = new CustomMethodListener();
		walker.walk(listener, tree);*/
	}

	
}
