using System.Collections.Generic;

public static class GuildPermission
{
	private static readonly int[] tempJobMemberNum;

	private static readonly bool[][] tempPermission;

	private static List<EnumData.GuildPermission> listPermission;

	public static void UpdatePermission()
	{
	}

	public static bool HasPermission(EnumData.GuildPermission permissionType)
	{
		return false;
	}

	public static bool HasPermission(EnumData.GuildJopType jobType, EnumData.GuildPermission permissionType)
	{
		return false;
	}

	public static bool[] GetPermission(EnumData.GuildJopType jobType)
	{
		return null;
	}

	public static int GetMaxJobMemberNum(EnumData.GuildJopType jobType)
	{
		return 0;
	}
}
