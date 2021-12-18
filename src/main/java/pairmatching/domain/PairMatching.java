package pairmatching.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PairMatching {
	private HashMap<Curriculum, List<Crew>> matchingLog;
	private CrewGroup crewGroup = new CrewGroup();

	public PairMatching() {

	}

	public void run(Curriculum curriculum) {
		for (String name : crewGroup.getShuffledCrews()) {
			List<Crew> crews = new LinkedList<>();
			crews.add(new Crew(curriculum.getCourse(), name));
			matchingLog.put(curriculum, crews);
		}
	}

	public List<List<Crew>> printMatching(Curriculum curriculum) {
		if (isEmpty(curriculum)) {
			throw new IllegalArgumentException();
		}
		return matchingLog.entrySet().stream()
			.filter(cur -> cur.getKey().equals(curriculum))
			.map(Map.Entry::getValue)
			.collect(Collectors.toList());
	}

	public boolean isEmpty(Curriculum curriculum) {
		Optional<Curriculum> curriculumOptional = matchingLog.keySet().stream()
			.filter(cur -> cur.equals(curriculum))
			.findAny();
		return curriculumOptional.isPresent();
	}
}
