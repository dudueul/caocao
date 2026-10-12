using UnityEngine;

namespace Game.Setting
{
	public abstract class BaseResolutionMetaData
	{
		public abstract Resolution[] supportedResolutions { get; }

		public abstract Resolution defaultResolution { get; }

		public abstract bool useSettingFullscreen { get; }

		public abstract bool useSettingResolution { get; }

		public string defaultResolutionText
		{
			get
			{
				return null;
			}
		}

		public int defaultWidth
		{
			get
			{
				return 0;
			}
		}

		public int defaultHeight
		{
			get
			{
				return 0;
			}
		}

		public BaseResolutionMetaData()
		{
		}

		protected abstract void Initialize();
	}
}
