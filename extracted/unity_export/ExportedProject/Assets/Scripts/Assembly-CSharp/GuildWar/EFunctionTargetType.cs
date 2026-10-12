namespace GuildWar
{
	public enum EFunctionTargetType
	{
		None = 0,
		Tile = 1,
		AllEnemies = 2,
		AllAlly = 3,
		MySelf = 4,
		OnlyCorpsEnemies = 5,
		OnlyCorpsAlly = 6,
		OnlyBuildingEnemies = 7,
		OnlyBuildingAlly = 8,
		OnlyCorpsAllyExcludeMySelf = 9,
		All = 10,
		EmptyTile = 11,
		MySelfNotMaxHP = 12,
		OnlyCorpsAllyNotMaxHP = 13,
		OnlyCorpsAllyWithDebuff = 14,
		TileWithEffect = 15
	}
}
