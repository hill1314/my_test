package com.hull.test.autoMethod.camel;

/**
 * Created by hull on 2017/1/19.
 */
public abstract class AbsProcessor {
    /*
     * （非 Javadoc）
     *
     * @see
     * cn.com.git.udmp.calc.camel.job.IExecJob#exec(cn.com.git.udmp.common.model
     * .DataObject)
     */

    public final <T extends DataObject> T doProcess(T sourceObj) {
        try {
            if (Boolean.TRUE.equals(sourceObj.get(UdmpCamelConstants.TERMINATE_FLAG))) {
//                logger.debug("发现终止条件,跳过当前processor");
                return sourceObj;
            }
            if (preconditions(sourceObj)) {
                T result = process(sourceObj);
                afterProcess(result);
                return result;
            } else {
                throw new RuntimeException();
            }
        } catch (NormalTerminateException e) {
//            logger.debug("发现终止条件,跳过后续processor");
            sourceObj.set(UdmpCamelConstants.TERMINATE_FLAG, true);
            return sourceObj;
//        } catch (UnSatisfiedCondition e) {
//            logger.error("不满足执行条件,跳过当前执行点");
//            throw e;
        } catch (RuntimeException e) {
//            logger.error("任务执行失败:{}", e.getMessage());
            throw e;
        } finally {

        }

    }

    public abstract <T extends DataObject> T process(T sourceObj);

    public abstract <T extends DataObject> void afterProcess(T sourceObj);

    public <T extends DataObject> boolean preconditions(T sourceObj) {
        // default
        return true;
    }
}
