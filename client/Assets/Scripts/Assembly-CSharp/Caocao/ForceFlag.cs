using System;
using System.ComponentModel;

namespace Caocao
{
	[Flags]
	public enum ForceFlag
	{
		[Description]
		OWN = 1,
		[Description]
		ALLY = 2,
		[Description]
		ENEMY = 4,
		[Description]
		ENEMYALLY = 8
	}
}
