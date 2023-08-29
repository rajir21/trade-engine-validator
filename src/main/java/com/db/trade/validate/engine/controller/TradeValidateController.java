package com.db.trade.validate.engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.trade.validate.engine.constants.Constants;
import com.db.trade.validate.engine.model.Trade;
import com.db.trade.validate.engine.service.TradeService;

@RestController
@RequestMapping("/validate")
public class TradeValidateController {
	
    @Autowired
    TradeService tradeService;
    
    @PostMapping(Constants.ISVALID)
    public ResponseEntity<Object> tradeIsValid(@RequestBody Trade trade){
        return ResponseEntity.ok(tradeService.isValid(trade));
    }
    
    @PostMapping(Constants.MATURITYDATE)
    public ResponseEntity<Boolean> validateMaturityDate(@RequestBody Trade trade){
        return ResponseEntity.ok(tradeService.validateMaturityDate(trade));
    }
}
