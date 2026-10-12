using UnityEngine;

namespace Game.Setting
{
	public class ResolutionAndroidMetaData : BaseResolutionMetaData
	{
		public override Resolution defaultResolution
		{
			get
			{
				return default(Resolution);
			}
		}

		public override Resolution[] supportedResolutions
		{
			get
			{
				return null;
			}
		}

		public override bool useSettingFullscreen
		{
			get
			{
				return false;
			}
		}

		public override bool useSettingResolution
		{
			get
			{
				return false;
			}
		}

		protected override void Initialize()
		{
		}
	}
}
