using System;
using UnityEngine;

namespace MAssist
{
	[Serializable]
	public class LogCategoryItem
	{
		public string name;

		public Color color;

		public int index;

		public bool isEnable;

		public bool isDefault;

		public bool isOverlap;
	}
}
