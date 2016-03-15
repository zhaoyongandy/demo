package net;

import java.util.Map;
import java.util.Set;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.agent.model.Service;

public class ConsulTest {

	public static void main(String[] args) {
		try {

			ConsulClient client = new ConsulClient("localhost");
			// 注册Consul服务
			NewService newService = new NewService();
			newService.setId("Chat_01");
			newService.setName("Chat");
			newService.setAddress("192.168.1.91");
			newService.setPort(8080);
			client.agentServiceRegister(newService);

			// 查找Consul服务
			Response<Map<String, Service>> response = client.getAgentServices();
			Map<String, Service> map = response.getValue();

			Set<String> keys = map.keySet();

			for (String key : keys) {
				Service service = map.get(key);
				System.out.println("系统将调用以下服务信息");
				System.out.println("id: " + service.getId());
				System.out.println("service: " + service.getService());
				System.out.println("tags: " + service.getTags());
				System.out.println("address: " + service.getAddress());
				System.out.println("port: " + service.getPort());
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
