import java.util.*;

public class Lottery {
	int ticketCount;
	int maxNumber;
	Set<Integer> winningNums;
	boolean drawn;
	
	Set<Ticket> tickets;

	public Lottery(int maxNumber) {
		this.maxNumber = maxNumber;
		this.ticketCount = 1;
		tickets = new HashSet<>();
	}

	public Ticket buyTicket(int[] numbers) {
		
		if (drawn) {
			throw new IllegalStateException();
		}
		Ticket ticket = new Ticket(numbers, ticketCount);
		
		Arrays.sort(numbers);
		
		if (numbers[0] < 1 || numbers[numbers.length - 1] > maxNumber || ticket.getNumbers().size() != 6) {
			throw new IllegalArgumentException();
		}
		
		tickets.add(ticket);
		
		++ticketCount;
		return ticket;
	}

	public int soldTickets() {
		return ticketCount - 1;
	}
	
	public static void main(String[] args) {
		Lottery lot = new Lottery(10);
		
		Ticket t1 = lot.buyTicket(new int[] {1, 2, 3, 4, 5, 6});
		
		System.out.println(t1.getNumbers());
		
		lot.draw();
		
		System.out.println(t1.getCorrectNumbers());
		
	}

	public void draw() {
		
		if (drawn) {
			throw new IllegalStateException();
		}
		
		drawn = true;
		winningNums = new HashSet<>();
		
		Random rand = new Random();

		while(winningNums.size() < 6) {
			int num = rand.nextInt(maxNumber);
			winningNums.add(num + 1);
		}
		
		for (Ticket t : tickets) {
			t.drawn = true;
			t.correctNums = winningNums;
		}
		
	}

	public Set<Integer> getWinning() {
		
		if (!drawn) {
			throw new IllegalStateException();
		}
		
		return winningNums;
	}
}
