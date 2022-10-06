package example9.test;

import example9.main.DatabaseException;
import example9.main.IDatabase;
import example9.main.TeamScoreUpdate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

public class TeamScoreUpdateTest {

    private IDatabase databaseMock = Mockito.mock(IDatabase.class);

    private TeamScoreUpdate teamScoreUpdate = new TeamScoreUpdate(databaseMock);

    @ParameterizedTest
    @MethodSource("generator")
    void test(String teamId, int[] scores, int totalScores) {
        teamScoreUpdate.calculateTotalAndStore(teamId, scores);
        Mockito.verify(databaseMock, Mockito.times(1)).updateScores(teamId, totalScores);
        Mockito.doThrow(new DatabaseException()).when(databaseMock).updateScores(teamId, totalScores);
    }

    static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of("Team1", new int[]{}, 0),
                Arguments.of("Team1", new int[]{100}, 100),
                Arguments.of("Team1", new int[]{100, 50}, 150)
        );
    }

}
