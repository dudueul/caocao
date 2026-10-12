using System;
using UnityEngine;

namespace tk2dRuntime.TileMap
{
	[Serializable]
	public class ColorChunk
	{
		public Color32[] colors;

		public bool Dirty { get; set; }

		public bool Empty
		{
			get
			{
				return false;
			}
		}
	}
}
