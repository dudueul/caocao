using System;
using UnityEngine;

namespace tk2dRuntime.TileMap
{
	[Serializable]
	public class ColorChannel
	{
		public Color clearColor;

		public ColorChunk[] chunks;

		public int numColumns;

		public int numRows;

		public int divX;

		public int divY;

		public bool IsEmpty
		{
			get
			{
				return false;
			}
		}

		public int NumActiveChunks
		{
			get
			{
				return 0;
			}
		}

		public ColorChannel(int width, int height, int divX, int divY)
		{
		}

		public ColorChannel()
		{
		}

		public void Init(int width, int height, int divX, int divY)
		{
		}

		public ColorChunk FindChunkAndCoordinate(int x, int y, out int offset)
		{
			offset = default(int);
			return null;
		}

		public Color GetColor(int x, int y)
		{
			return default(Color);
		}

		private void InitChunk(ColorChunk chunk)
		{
		}

		public void SetColor(int x, int y, Color color)
		{
		}

		public ColorChunk GetChunk(int x, int y)
		{
			return null;
		}

		public ColorChunk GetChunk(int x, int y, bool init)
		{
			return null;
		}

		public void ClearChunk(ColorChunk chunk)
		{
		}

		public void ClearDirtyFlag()
		{
		}

		public void Clear(Color color)
		{
		}

		public void Delete()
		{
		}

		public void Create()
		{
		}

		private void Optimize(ColorChunk chunk)
		{
		}

		public void Optimize()
		{
		}
	}
}
