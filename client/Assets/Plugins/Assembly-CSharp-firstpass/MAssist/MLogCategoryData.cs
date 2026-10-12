using System;
using System.Collections.Generic;
using UnityEngine;

namespace MAssist
{
	[Serializable]
	public class MLogCategoryData
	{
		public List<LogCategoryItem> categoryItemList;

		public Color GetCategoryColor(string name)
		{
			return default(Color);
		}
	}
}
