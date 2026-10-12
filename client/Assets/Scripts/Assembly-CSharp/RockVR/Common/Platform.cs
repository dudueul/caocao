namespace RockVR.Common
{
	public class Platform
	{
		public const PlatformType CURRENT_PLATFORM = PlatformType.ANDROID;

		public static bool IsSupported(PlatformType platform)
		{
			return false;
		}
	}
}
