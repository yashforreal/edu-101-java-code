package helloworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

/* 
 * To run the program: mvn exec:java -Dexec.mainClass="helloworkflow.HelloWorkflowWorker" -Dexec.args="Mason"
 */

// Option (A) - Starting workflow using cli:

/* public class HelloWorkflowWorker {
    
    public static void main(String[] args) {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // TODO: modify the statement below to specify the task queue name
        Worker worker = factory.newWorker("greeting-tasks");

        worker.registerWorkflowImplementationTypes(HelloWorkflowWorkflowImpl.class);

        factory.start();
    }
} */

/* 
 * For above code, run the following command on the cli to start the Workflow:
 *          temporal workflow start \
                --type HelloWorkflowWorkflow \
                --task-queue greeting-tasks \
                --workflow-id my-first-workflow \
                --input '"Mason"'
 */

 /* 
  * To view the Workflow result and other details, run the following command:
        temporal workflow show --workflow-id my-first-workflow
  */

 // Option (B) - Starting the workflow in-application

 public class HelloWorkflowWorker {
    
    public static void main(String[] args) {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // TODO: modify the statement below to specify the task queue name
        Worker worker = factory.newWorker("greeting-tasks");

        worker.registerWorkflowImplementationTypes(HelloWorkflowWorkflowImpl.class);

        factory.start();

        WorkflowOptions options = WorkflowOptions.newBuilder()
                    .setWorkflowId("my-first-workflow")
                    .setTaskQueue("greeting-tasks")
                    .build();
       
        HelloWorkflowWorkflow workflow = client.newWorkflowStub(HelloWorkflowWorkflow.class, options);

        String greeting = workflow.greetSomeone(args[0]);

        String workflowId = WorkflowStub.fromTyped(workflow).getExecution().getWorkflowId();

        System.out.println(workflowId + " " + greeting);
    }
}