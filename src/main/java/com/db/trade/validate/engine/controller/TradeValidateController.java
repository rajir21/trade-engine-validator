package com.db.trade.validate.engine.controller;

import com.db.trade.validate.engine.constants.Constants;
import com.db.trade.validate.engine.model.Trade;
import com.db.trade.validate.engine.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/validate")
public class TradeValidateController {
	
    @Autowired
    TradeService tradeService;
    
    @PostMapping(Constants.ISVALID)
    public ResponseEntity<Object> tradeIsValid(@Valid @RequestBody Trade trade){

        return ResponseEntity.ok(tradeService.isValid(tradeService.combincationCheck(trade)));
    }
    @PostMapping(Constants.VALIDATOR)
    public ResponseEntity<Object> validator(@Valid @RequestBody Trade trade){
       // based on statsu return true /false
        return ResponseEntity.ok(tradeService.isValid(tradeService.combincationCheck(trade)));
    }

    @PostMapping(Constants.TRADE_STATUS)
    public ResponseEntity<Object> tradeStatus(@Valid @RequestBody Trade trade){
        Trade  tradeObj = (Trade) tradeService.combincationCheck(trade);
        Object strObj = tradeObj.getStatus();
       /* if(tradeObj.getStatus()!=null)
            if(tradeObj.getStatus().equals("INSERT")  ) {
                return ResponseEntity.ok("INSERT");
            }
            if(tradeObj.getStatus().equals("UPDATE")  ) {
                return ResponseEntity.ok("UPDATE");
            } */
        //return ResponseEntity.ok(tradeService.combincationCheck(trade));
        return ResponseEntity.ok(strObj);

    }


    @PostMapping(Constants.MATURITYDATE)
    public ResponseEntity<Boolean> validateMaturityDate(@Valid @RequestBody Trade trade){
        return ResponseEntity.ok(tradeService.validateMaturityDate(trade));
    }
}
