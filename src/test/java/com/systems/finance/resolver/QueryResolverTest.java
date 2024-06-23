package com.systems.finance.resolver;

import com.systems.finance.exception.UserNotFoundException;
import com.systems.finance.model.Expense;
import com.systems.finance.model.Income;
import com.systems.finance.model.User;
import com.systems.finance.repository.*;
import org.dataloader.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QueryResolverTest {

    @Mock
    private InvestmentRepository investmentRepository;

    @Mock
    private SavingsGoalRepository savingsGoalRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private DataLoader<Long, User> userLoader;

    @InjectMocks
    private QueryResolver queryResolver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUser() {
        User user = new User();
        user.setId(1L);
        when(userLoader.load(1L)).thenReturn(CompletableFuture.completedFuture(user));

        User result = queryResolver.getUser(1L);
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void testGetUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> queryResolver.getUser(1L));
    }

    @Test
    void testGetIncomes() {
        Income income = new Income();
        income.setId(1L);
        when(incomeRepository.findByUserId(1L)).thenReturn(Arrays.asList(income));

        List<Income> result = queryResolver.getIncomes(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(income, result.get(0));
    }

    @Test
    void testGetExpenses() {
        Expense expense = new Expense();
        expense.setId(1L);
        when(expenseRepository.findByUserId(1L)).thenReturn(Arrays.asList(expense));

        List<Expense> result = queryResolver.getExpenses(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expense, result.get(0));
    }

    @Test
    void testGetExpensesPaginated() {
        Expense expense = new Expense();
        expense.setId(1L);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Expense> page = new PageImpl<>(Arrays.asList(expense), pageable, 1);
        when(expenseRepository.findByUserId(1L, pageable)).thenReturn(page);

        Page<Expense> result = queryResolver.getExpensesPaginated(1L, 0, 10, "id");
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(expense, result.getContent().get(0));
    }
}
