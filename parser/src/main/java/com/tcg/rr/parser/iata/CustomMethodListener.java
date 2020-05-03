package com.tcg.rr.parser.iata;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;

import com.example.PnlAdlBaseListener;
import com.example.PnlAdlParser.ElementContext;
import com.example.PnlAdlParser.End_elemContext;
import com.example.PnlAdlParser.FlightContext;
import com.example.PnlAdlParser.Flight_elemContext;
import com.example.PnlAdlParser.ListContext;
import com.example.PnlAdlParser.Message_idContext;
import com.example.PnlAdlParser.Totals_by_dest_elemContext;

public class CustomMethodListener extends PnlAdlBaseListener {

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		super.enterEveryRule(ctx);
		System.out.println("Entry...");
		
		int cc = ctx.getChildCount();
		int ri = ctx.getRuleIndex();
		String t = ctx.getText();
		int an = ctx.getAltNumber();
		System.out.println("Child count: " + cc + ", Rule index: " + ri+ ", Text: "
				+ t + ", Alt number: " + an );
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		super.exitEveryRule(ctx);
		System.out.println("Exit...\n");
	}

	@Override
	public void enterList(ListContext ctx) {
		// TODO Auto-generated method stub
		super.enterList(ctx);
		ctx.getStart().getType();
		//ctx.get
		//System.out.println(ctx.);
	}

	@Override
	public void enterElement(ElementContext ctx) {
		// TODO Auto-generated method stub
		super.enterElement(ctx);
	}

	@Override
	public void enterMessage_id(Message_idContext ctx) {
		// TODO Auto-generated method stub
		super.enterMessage_id(ctx);
	}

	@Override
	public void enterFlight_elem(Flight_elemContext ctx) {
		// TODO Auto-generated method stub
		super.enterFlight_elem(ctx);
	}

	@Override
	public void enterFlight(FlightContext ctx) {
		// TODO Auto-generated method stub
		super.enterFlight(ctx);
	}

	@Override
	public void enterTotals_by_dest_elem(Totals_by_dest_elemContext ctx) {
		// TODO Auto-generated method stub
		super.enterTotals_by_dest_elem(ctx);
	}

	@Override
	public void enterEnd_elem(End_elemContext ctx) {
		// TODO Auto-generated method stub
		super.enterEnd_elem(ctx);
	}
	
	

}
