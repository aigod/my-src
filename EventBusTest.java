import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

//事件
class MessageEvent {
	private String name;
	private int age;
	
	public MessageEvent() {}
	public MessageEvent(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
}


//处理逻辑
class EventListener {
	private final String filter;
	
	public EventListener(String filter) {
		this.filter = filter;
	}
	
	@Subscribe
    public void listen(MessageEvent event) {
		if(filter != null) {
			if(filter.equals(event.getName())) {
				System.out.println("filter: "+event.getName()+": "+event.getName());
			} else {
				System.out.println("not match! filtered!");
			}
		} else {
			System.out.println(event.getName()+": "+event.getName());
		}
    } 
}


//事件总线
public class EventBusTest {
	
	public static void main(String[] args) {
		
		EventBus eventBus = new EventBus("test");
		
		EventListener ls1 = new EventListener("te");
		EventListener ls2 = new EventListener(null);
		
		eventBus.register(ls1);
		eventBus.register(ls2);
		
		eventBus.post(new MessageEvent("hello",11));
		
	}
}
