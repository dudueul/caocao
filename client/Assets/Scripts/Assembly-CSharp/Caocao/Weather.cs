using System;

namespace Caocao
{
	[Flags]
	public enum Weather
	{
		serenity = 1,
		cloud = 2,
		rain = 4,
		snow = 8,
		storm = 0x10,
		fog = 0x20,
		random = 0x40
	}
}
