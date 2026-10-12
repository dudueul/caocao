using System;
using UnityEngine;

namespace tk2dRuntime.TileMap
{
	[Serializable]
	public class Layer
	{
		public int hash;

		public SpriteChannel spriteChannel;

		private const int tileMask = 16777215;

		private const int flagMask = -16777216;

		public int width;

		public int height;

		public int numColumns;

		public int numRows;

		public int divX;

		public int divY;

		public GameObject gameObject;

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

		public Layer(int hash, int width, int height, int divX, int divY)
		{
		}

		public void Init(int hash, int width, int height, int divX, int divY)
		{
		}

		public void Create()
		{
		}

		public int[] GetChunkData(int x, int y)
		{
			return null;
		}

		public SpriteChunk GetChunk(int x, int y)
		{
			return null;
		}

		private SpriteChunk FindChunkAndCoordinate(int x, int y, out int offset)
		{
			offset = default(int);
			return null;
		}

		private bool GetRawTileValue(int x, int y, ref int value)
		{
			return false;
		}

		private void SetRawTileValue(int x, int y, int value)
		{
		}

		public void DestroyGameData(tk2dTileMap tilemap)
		{
		}

		public int GetTile(int x, int y)
		{
			return 0;
		}

		public tk2dTileFlags GetTileFlags(int x, int y)
		{
			return tk2dTileFlags.None;
		}

		public int GetRawTile(int x, int y)
		{
			return 0;
		}

		public void SetTile(int x, int y, int tile)
		{
		}

		public void SetTileFlags(int x, int y, tk2dTileFlags flags)
		{
		}

		public void ClearTile(int x, int y)
		{
		}

		public void SetRawTile(int x, int y, int rawTile)
		{
		}

		private void CreateChunk(SpriteChunk chunk)
		{
		}

		private void Optimize(SpriteChunk chunk)
		{
		}

		public void Optimize()
		{
		}

		public void OptimizeIncremental()
		{
		}

		public void ClearDirtyFlag()
		{
		}
	}
}
