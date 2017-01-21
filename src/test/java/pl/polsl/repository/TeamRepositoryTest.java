package pl.polsl.repository;

import org.junit.Test;

import pl.polsl.bonus.model.Team;

public class TeamRepositoryTest {

	@Test
	public void testCreateTeam() {
		Team team = new Team();
		team.setTeamId(1);
		team.setTeamManager(null);
		team.setTeamName("Test team");
		
	}

}
