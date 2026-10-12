using UnityEngine;

public static class tk2dSpriteGeomGen
{
	private static readonly int[] boxIndicesBack;

	private static readonly int[] boxIndicesFwd;

	private static readonly Vector3[] boxUnitVertices;

	private static Matrix4x4 boxScaleMatrix;

	public static void SetSpriteColors(Color32[] dest, int offset, int numVertices, Color c, bool premulAlpha)
	{
	}

	public static Vector2 GetAnchorOffset(tk2dBaseSprite.Anchor anchor, float width, float height)
	{
		return default(Vector2);
	}

	public static void GetSpriteGeomDesc(out int numVertices, out int numIndices, tk2dSpriteDefinition spriteDef)
	{
		numVertices = default(int);
		numIndices = default(int);
	}

	public static void SetSpriteGeom(Vector3[] pos, Vector2[] uv, Vector3[] norm, Vector4[] tang, int offset, tk2dSpriteDefinition spriteDef, Vector3 scale)
	{
	}

	public static void SetSpriteIndices(int[] indices, int offset, int vStart, tk2dSpriteDefinition spriteDef)
	{
	}

	public static void GetClippedSpriteGeomDesc(out int numVertices, out int numIndices, tk2dSpriteDefinition spriteDef)
	{
		numVertices = default(int);
		numIndices = default(int);
	}

	public static void SetClippedSpriteGeom(Vector3[] pos, Vector2[] uv, int offset, out Vector3 boundsCenter, out Vector3 boundsExtents, tk2dSpriteDefinition spriteDef, Vector3 scale, Vector2 clipBottomLeft, Vector2 clipTopRight, float colliderOffsetZ, float colliderExtentZ)
	{
		boundsCenter = default(Vector3);
		boundsExtents = default(Vector3);
	}

	public static void SetClippedSpriteIndices(int[] indices, int offset, int vStart, tk2dSpriteDefinition spriteDef)
	{
	}

	public static void GetSlicedSpriteGeomDesc(out int numVertices, out int numIndices, tk2dSpriteDefinition spriteDef, bool borderOnly)
	{
		numVertices = default(int);
		numIndices = default(int);
	}

	public static void SetSlicedSpriteGeom(Vector3[] pos, Vector2[] uv, int offset, out Vector3 boundsCenter, out Vector3 boundsExtents, tk2dSpriteDefinition spriteDef, Vector3 scale, Vector2 dimensions, Vector2 borderBottomLeft, Vector2 borderTopRight, tk2dBaseSprite.Anchor anchor, float colliderOffsetZ, float colliderExtentZ)
	{
		boundsCenter = default(Vector3);
		boundsExtents = default(Vector3);
	}

	public static void SetSlicedSpriteIndices(int[] indices, int offset, int vStart, tk2dSpriteDefinition spriteDef, bool borderOnly)
	{
	}

	public static void GetTiledSpriteGeomDesc(out int numVertices, out int numIndices, tk2dSpriteDefinition spriteDef, Vector2 dimensions)
	{
		numVertices = default(int);
		numIndices = default(int);
	}

	public static void SetTiledSpriteGeom(Vector3[] pos, Vector2[] uv, int offset, out Vector3 boundsCenter, out Vector3 boundsExtents, tk2dSpriteDefinition spriteDef, Vector3 scale, Vector2 dimensions, tk2dBaseSprite.Anchor anchor, float colliderOffsetZ, float colliderExtentZ)
	{
		boundsCenter = default(Vector3);
		boundsExtents = default(Vector3);
	}

	public static void SetTiledSpriteIndices(int[] indices, int offset, int vStart, tk2dSpriteDefinition spriteDef, Vector2 dimensions)
	{
	}

	public static void SetBoxMeshData(Vector3[] pos, int[] indices, int posOffset, int indicesOffset, int vStart, Vector3 origin, Vector3 extents, Matrix4x4 mat, Vector3 baseScale)
	{
	}

	public static void SetSpriteDefinitionMeshData(Vector3[] pos, int[] indices, int posOffset, int indicesOffset, int vStart, tk2dSpriteDefinition spriteDef, Matrix4x4 mat, Vector3 baseScale)
	{
	}

	public static void SetSpriteVertexNormals(Vector3[] pos, Vector3 pMin, Vector3 pMax, Vector3[] spriteDefNormals, Vector4[] spriteDefTangents, Vector3[] normals, Vector4[] tangents)
	{
	}
}
