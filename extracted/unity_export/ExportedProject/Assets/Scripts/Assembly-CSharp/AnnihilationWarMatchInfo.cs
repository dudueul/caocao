using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class AnnihilationWarMatchInfo
{
	private List<AnnihilationWarEnemy> m_enemies;

	public List<AnnihilationWarEnemy> enemies
	{
		get
		{
			return null;
		}
	}

	public ObscuredInt refreshGold { get; set; }

	public ObscuredBool isOwnerFirst { get; set; }

	public AnnihilationWarEnemy selectedEnemy { get; set; }

	public ObscuredInt ticketFee { get; set; }

	public ObscuredInt ticketCooltime { get; set; }

	public ulong ticketCooltimeMs
	{
		get
		{
			return 0uL;
		}
	}

	public void Sort()
	{
	}
}
