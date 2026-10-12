using System;

public static class TutorialUtil
{
	public enum TutorialType
	{
		None = 0,
		WorldMap = 1,
		BattleEnd = 2,
		Chronicle = 3,
		Town = 4,
		Recon = 5,
		AnnihilationWar = 6,
		ThousandBattle = 7,
		Instance = 8,
		GuildSiegeWar = 9,
		Competition = 10,
		Arena = 11,
		GuildWar1 = 12,
		GuildWar2 = 13
	}

	private static TutorialType lastTutorialScriptType;

	private static string GetTutoCheckScriptName(TutorialType tutoType)
	{
		return null;
	}

	public static void RunTutorialCheckingScript(TutorialType tutoType, Action callback)
	{
	}

	public static bool DidCompleteTutorialBattle()
	{
		return false;
	}
}
