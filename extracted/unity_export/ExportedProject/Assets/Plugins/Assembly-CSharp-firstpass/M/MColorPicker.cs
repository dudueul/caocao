using System;
using UnityEngine;

namespace M
{
	public class MColorPicker
	{
		private static readonly float UIWidth;

		private static readonly float UIHeight;

		private static readonly Rect MinRect;

		public Rect rect;

		public Texture2D colorPicker;

		private Texture2D displayPicker;

		private bool _isMouseDown;

		public Color setColor;

		private Color prevColor;

		public int textureWidth;

		public int textureHeight;

		private float saturationSlider;

		private Texture2D saturationTexture;

		private Texture2D styleTexture;

		public bool isShowSaturation;

		public Action<Color> ChangeColorAction;

		public MColorPicker(bool isShowSaturation = true)
		{
		}

		public void buildTexture()
		{
		}

		public void SetRect(Rect rect)
		{
		}

		public void OnGUI()
		{
		}
	}
}
