package com.db.trade.validate.engine.dao;

import com.db.trade.validate.engine.model.Trade;
import com.mongodb.MongoException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeRepository extends MongoRepository<Trade,String> {

	//@Query(value = "{ 'tradeId' : ?0 }",sort = "{'version': -1}")
	@Query(value = "{ 'tradeId' : ?0 }")
	Optional<Trade> findByTradeId(String tradeId) throws MongoException;

	@Query(value = "{ 'expiredFlag' : ?0 }")
	List<Trade> findAllExpiryFlagN(String expiredFlag) throws MongoException;
}
