using UnityEngine;

namespace Game.Setting
{
	public class ResolutionStandaloneMetaData : BaseResolutionMetaData
	{
		private readonly Resolution[] m_metaData;

		private Resolution[] m_supportedResolutions;

		private Resolution m_defaultResolution;

		public override Resolution[] supportedResolutions
		{
			get
			{
				return null;
			}
		}

		public override Resolution defaultResolution
		{
			get
			{
				return default(Resolution);
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
