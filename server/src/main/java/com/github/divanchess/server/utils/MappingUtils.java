package com.github.divanchess.server.utils;

import com.github.divanchess.dto.AccountDto;
import com.github.divanchess.dto.BalanceDto;
import com.github.divanchess.dto.CardDto;
import com.github.divanchess.server.entity.Account;
import com.github.divanchess.server.entity.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingUtils {
    //из entity в dto
    public static CardDto mapCardEntityToCardDto(Card entity){
        CardDto dto = new CardDto();
        dto.setPan(entity.getPan());
        dto.setPin(entity.getPin());
        List<Account> accountList = entity.getAccounts();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account account : accountList) {
            accountDtoList.add(new AccountDto(account.getId(),account.getNumber(),new BalanceDto(account.getBalance())));
        }
        dto.setAccountDtoList(accountDtoList);
        return dto;
    }

    //из dto в entity
    public Card mapCardDtoToCardEntity(CardDto dto){
        Card entity = new Card();
        entity.setPan(dto.getPan());
        entity.setPin(dto.getPin());
        List<Account> accountList = new ArrayList<>();
        List<AccountDto> accountDtoSet = dto.getAccountDtoList();
        for (AccountDto accountDto : accountDtoSet) {
            Account account = new Account();
            account.setNumber(accountDto.getNumber());
            account.setId(accountDto.getAccountId());
            account.setBalance(accountDto.getBalance().getAmount());
            accountList.add(account);
        }
        entity.setAccounts(accountList);
        return entity;
    }
}