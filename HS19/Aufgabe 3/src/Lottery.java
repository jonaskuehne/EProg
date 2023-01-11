import java.util.*;

public class Lottery {
	// numbers
	int ticketCount;
	int maxNumber;
	// sets
	Set<Ticket> tickets;
	Set<Integer> winningNums;
	// game state
	boolean drawn;
	
	// ctor
	public Lottery(int maxNumber) {
		this.maxNumber = maxNumber;
		this.ticketCount = 1;
		// keep track of sold tickets
		tickets = new HashSet<>();
	}

	public Ticket buyTicket(int[] numbers) {
		
		if (drawn) {
			throw new IllegalStateException();
		}
		
		Ticket ticket = new Ticket(numbers, ticketCount);
		
		// check if range correct and numbers unique
		Arrays.sort(numbers);
		if (numbers[0] < 1 || numbers[numbers.length - 1] > maxNumber || ticket.getNumbers().size() != 6) {
			throw new IllegalArgumentException();
		}
		
		// add to set
		tickets.add(ticket);
		
		++ticketCount;
		return ticket;
	}

	public int soldTickets() {
		// since we start at 1
		return ticketCount - 1;
	}

	public void draw() {
		
		if (drawn) {
			throw new IllegalStateException();
		}
		
		// set state
		drawn = true;
		winningNums = new HashSet<>();
		
		Random rand = new Random();
		
		// while not 6 different nums
		while(winningNums.size() < 6) {
			int num = rand.nextInt(maxNumber);
			winningNums.add(num + 1);
		}
		
		// change state of tickets
		for (Ticket t : tickets) {
			t.drawn = true;
			t.setCorrectNums(winningNums);
		}
		
	}

	public Set<Integer> getWinning() {
		
		if (!drawn) {
			throw new IllegalStateException();
		}
		
		return winningNums;
	}
}
