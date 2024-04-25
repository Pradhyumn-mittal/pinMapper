package ServiceTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.java.pinMapper.entity.constant.unit.test.CacheServiceTestVariable;
import com.java.pinMapper.service.impl.CacheServiceImpl;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

public class CacheServiceImplTest extends CacheServiceTestVariable {
  @InjectMocks
  private CacheServiceImpl cacheService;

  @Mock
  private RedisTemplate redisTemplate;
  @Mock
  private ValueOperations<String, String> valueOperations;

  @Mock
  private SetOperations<String, String> setOperations;
  @Test
  public void findCacheByKeyTestSuccess() {
    when(valueOperations.get(KEY)).thenReturn(VALUE);

    String value = cacheService.findCacheByKey(KEY, String.class);

    verify(redisTemplate).opsForValue();
    verify(valueOperations).get(KEY);

    assertEquals(VALUE, value);
  }

  @Test
  public void findCacheByKeyTestNoResult() {
    when(valueOperations.get(KEY)).thenReturn(null);

    String value = cacheService.findCacheByKey(KEY, String.class);

    verify(redisTemplate).opsForValue();
    verify(valueOperations).get(KEY);

    assertEquals(null, value);
  }

  @Test
  public void findCacheByKeyTestException() {
    when(redisTemplate.opsForValue()).thenThrow(new RuntimeException("Error"));

    String value = cacheService.findCacheByKey(KEY, String.class);

    verify(redisTemplate).opsForValue();

    assertEquals(null, value);
  }

  @Test
  public void createCacheTestSuccessWithNoExpired() {
  Boolean status=cacheService.createCache(KEY, VALUE, 0);

    verify(redisTemplate).opsForValue();
    verify(valueOperations).set(KEY, VALUE);

    assertEquals(true, status);
  }
  @Test
  public void createCacheTestSuccessWithExpiryTime() {
    Boolean status=cacheService.createCache(KEY, VALUE, EXPIRED_CACHE);

    verify(redisTemplate).opsForValue();
    verify(valueOperations).set(KEY, VALUE, EXPIRED_CACHE, TimeUnit.SECONDS);

    assertEquals(true, status);
  }

  @Test
  public void createCacheTestException() {
    when(redisTemplate.opsForValue()).thenThrow(new RuntimeException("Error"));
    Boolean status= cacheService.createCache(KEY, VALUE, EXPIRED_CACHE);

    verify(redisTemplate).opsForValue();

    assertEquals(false, status);
  }
  @Before
  public void setUp() {
    initMocks(this);
    when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    when(redisTemplate.opsForSet()).thenReturn(setOperations);
 
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(redisTemplate);
    verifyNoMoreInteractions(valueOperations);
    verifyNoMoreInteractions(setOperations);
  }
}
