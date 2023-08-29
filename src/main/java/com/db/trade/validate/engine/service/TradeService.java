package com.db.trade.validate.engine.service;

import com.db.trade.validate.engine.constants.Constants;
import com.db.trade.validate.engine.dao.TradeRepository;
import com.db.trade.validate.engine.dao.TradeUpdateRepository;
import com.db.trade.validate.engine.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TradeService {

	private static final Logger log = LoggerFactory.getLogger(TradeService.class);

	@Autowired
	TradeRepository tradeRepository;

	@Autowired
	TradeUpdateRepository tradeUpdateRepository;

	public Object isValid(Trade trade){
		if(validateMaturityDate(trade)) {
			try {
				Optional<Trade> exsitingTrade = tradeRepository.findByTradeId(trade.getTradeId());
				if (exsitingTrade.isPresent()) {
					return validateVersion(trade, exsitingTrade.get());
				}else{
					return true;
				}
			}catch(Exception e) {
				audit(trade);
				log.error("Exception Occured in isValid {}",Constants.ERROR,e.getMessage());
				// throw new ApiRequestException(Constants.ERROR);
			}
		}
		return false;
	}
	/*validation 1  During transmission if the
	lower version is being received by the store it will reject the trade and throw an exception.*/
	private boolean validateVersion(Trade trade,Trade oldTrade) {
		if(trade.getVersion() >= oldTrade.getVersion()){
			return true;
		}
		return false;
	}

	//Store should not allow the trade which has less maturity date then today date
	public boolean validateMaturityDate(Trade trade){
		return trade.getMaturityDate().isBefore(LocalDate.now())  ? false:true;
	}

	public void audit(Trade trade) {
		trade.setException(Constants.ERROR);
		tradeUpdateRepository.audit(trade);
	}

}
