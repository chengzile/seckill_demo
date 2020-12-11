-- 存储过程
DELIMITER $$

CREATE PROCEDURE seckill.execute_seckill(
in v_seckill_id BIGINT,
in v_phone BIGINT,
in v_kill_time TIMESTAMP,
out r_result INT
)
BEGIN
DECLARE insert_count INT DEFAULT 0;
START TRANSACTION;
INSERT IGNORE INTO success_killed(seckill_id,user_phone,state)
VALUES (v_seckill_id,v_phone,0);
SELECT ROW_COUNT() INTO insert_count;  -- 返回上一条语句的影响函数
IF(insert_count=0)THEN
ROLLBACK;
SET r_result=-1;
ELSEIF(insert_count<0)THEN
ROLLBACK;
SET r_result=-2;
ELSE
UPDATE seckill
SET number=number-1
WHERE seckill_id=v_seckill_id
AND strart_time <= v_kill_time
AND end_time >= v_kill_time
AND number > 0;
SELECT ROW_COUNT() INTO insert_count;
IF(insert_count=0)THEN
ROLLBACK;
SET r_result=0;
ELSEIF(insert_count<0)THEN
ROLLBACK;
SET r_result=-2;
ELSE
COMMIT;
SET r_result=1;
END IF;
END IF;
END;
$$


DELIMITER ;

SET @r_result=-3;
CALL execute_seckill(1003,13555555555,NOW(),@r_result);

SELECT @r_result;


 