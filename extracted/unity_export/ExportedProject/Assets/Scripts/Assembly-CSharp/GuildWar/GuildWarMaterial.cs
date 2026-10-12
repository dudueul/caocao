using UnityEngine;

namespace GuildWar
{
	public class GuildWarMaterial : MonoBehaviour
	{
		private static int? m_mainTexId;

		private static int? m_alphaDyeTexId;

		private static int? m_dyeAlphaTexId;

		private static int? m_alphaTexId;

		private static int? m_dyeTexId;

		private static int? m_redColorId;

		private static int? m_greenColorId;

		private static int? m_blueColorId;

		private static int? m_statusColorPropID;

		private static int? m_meshBoundsPropID;

		private static int? m_maskTexturePropID;

		private static int? m_colorPropID;

		private MaterialPropertyBlock _matProp;

		public static int mainTexId
		{
			get
			{
				return 0;
			}
		}

		public static int alphaDyeTexId
		{
			get
			{
				return 0;
			}
		}

		public static int dyeAlphaTexId
		{
			get
			{
				return 0;
			}
		}

		public static int alphaTexId
		{
			get
			{
				return 0;
			}
		}

		public static int dyeTexId
		{
			get
			{
				return 0;
			}
		}

		public static int redColorId
		{
			get
			{
				return 0;
			}
		}

		public static int greenColorId
		{
			get
			{
				return 0;
			}
		}

		public static int blueColorId
		{
			get
			{
				return 0;
			}
		}

		public static int statusColorPropID
		{
			get
			{
				return 0;
			}
		}

		public static int meshBoundsPropID
		{
			get
			{
				return 0;
			}
		}

		public static int maskTexturePropID
		{
			get
			{
				return 0;
			}
		}

		public static int colorPropID
		{
			get
			{
				return 0;
			}
		}

		public void SetCorpsMaterial(Color32[] _corpsColor, string hpRatio, string corpsPrefab)
		{
		}

		public void SetBuildingMaterial(Color32[] _buildingColor, string animationName, string BuildingPrefab)
		{
		}
	}
}
