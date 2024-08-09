package com.example.movement.service;

import com.example.movement.dto.MovementDTO;
import com.example.movement.dto.MovementType;
import com.example.movement.entity.Account;
import com.example.movement.entity.Movement;
import com.example.movement.exceptions.ExceedsBalanceException;
import com.example.movement.exceptions.NotFoundException;
import com.example.movement.repository.AccountRepository;
import com.example.movement.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovementService {

    private final MovementRepository repository;
    private final AccountRepository accountRepository;

    public List<Movement> findAll() {
        return repository.findAll();
    }

    public Movement save(MovementDTO movementDTO) {
        Account account = accountRepository.findByNumber(movementDTO.getAccountNumber())
                .orElseThrow(() -> new NotFoundException("Account with number " + movementDTO.getAccountNumber() + " not found"));
        Double newBalance = account.getAvailableBalance();
        if (MovementType.INCREASE.equals(movementDTO.getType())) {
            newBalance = newBalance + movementDTO.getAmount();
        } else {
            newBalance = newBalance - movementDTO.getAmount();
            if (newBalance < 0)
                throw new ExceedsBalanceException("Amount exceeds the balance");
        }

        account.setAvailableBalance(newBalance);
        Movement movement = Movement.builder()
                .account(account)
                .type(movementDTO.getType())
                .amount(movementDTO.getAmount())
                .balance(newBalance)
                .timestamp(LocalDateTime.now())
                .build();

        return repository.save(movement);
    }

    public Optional<Movement> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}