package be.pxl.ja.opgave1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opgave1 {
	public static void main(String[] args) throws IOException {
		CustomerRepository customerRepository = new CustomerRepository();
		System.out.println("*** Klanten uit Louisville:");
		List<Customer> allCustomers = customerRepository.findAll();
		System.out.println(allCustomers.stream().filter(customer -> customer.getCity().equalsIgnoreCase("louisville")).count());

		System.out.println("*** Jarige klanten: ");
		allCustomers.stream()
				.filter(customer -> customer.getDateOfBirth().isEqual(LocalDate.now()))
				.forEach(customer -> System.out.printf("%s %s %s", customer.getName(), customer.getFirstName(), customer.getDateOfBirth()));

		System.out.println("*** 10 jongste klanten:");
		allCustomers.stream()
				.sorted(Comparator.comparing(Customer::getDateOfBirth).reversed())
				.limit(10)
				.forEach(System.out::println);

		ActivityProcessor activityFileProcessor = new ActivityProcessor();
		List<Activity> allActivities = new ArrayList<>();
		Path errorFile = Path.of(System.getProperty("user.dir"), "resources", "opgave1", "log", "errors.log");
		List<Path> files = Files.list(Path.of(System.getProperty("user.dir"), "resources", "opgave1")).collect(Collectors.toList());
		files.forEach(file -> {
			try {
				allActivities.addAll(activityFileProcessor.processActivities(file, errorFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		// TODO: 4
		

		System.out.println("*** Top 10 klanten");
		Map<String, List<Activity>> activitiesPerCustomer = new HashMap<>();
		allActivities.forEach(activity -> {
			String key = activity.getCustomerNumber();
			if (activitiesPerCustomer.containsKey(key)) {
				activitiesPerCustomer.get(key).add(activity);
			} else {
				((List<Activity>) new ArrayList<Activity>()).add(activity);
				activitiesPerCustomer.put(key, new ArrayList<Activity>());
			}
		});
		activitiesPerCustomer.forEach((customerNumber, activityList) -> {
			Customer customer = customerRepository.getByCustomerNumber(customerNumber);
			int totalPoints = activityList.stream().mapToInt(activity -> (int) (activity.getActivityType().getPointsPerKm() * activity.getDistance())).sum();
			customer.addPoints(totalPoints);
		});
		customerRepository.findAll().stream().sorted(Comparator.comparing(Customer::getPoints).reversed()).limit(10).forEach(System.out::println);
		
		System.out.println("** Alle activiteiten meest actieve klant (gesorteerd op datum):");
		Optional<Customer> bestCustomer = customerRepository.findAll().stream().max(Comparator.comparing(Customer::getPoints));
		if (bestCustomer.isPresent()) {
			String customerNumber = bestCustomer.get().getCustomerNumber();
			allActivities.stream()
					.filter(activity -> activity.getCustomerNumber().equals(customerNumber))
					.sorted(Comparator.comparing(Activity::getActivityDate).reversed())
					.forEach(activity -> System.out.printf("%s %s %d %n", activity.getActivityDate(), activity.getActivityType(), (int) (activity.getDistance() * activity.getActivityType().getPointsPerKm())));
		}
	
	}

	static class AgeCalculator {
		public static int calculateAge(LocalDate birthDate) {
			LocalDate currentDate = LocalDate.now();
			if ((birthDate != null) && (currentDate != null)) {
				return Period.between(birthDate, currentDate).getDays();
			} else {
				return 0;
			}
		}
	}
}
