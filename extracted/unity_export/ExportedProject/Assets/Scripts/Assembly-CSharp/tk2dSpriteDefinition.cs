using System;
using UnityEngine;

[Serializable]
public class tk2dSpriteDefinition
{
	public enum ColliderType
	{
		Unset = 0,
		None = 1,
		Box = 2,
		Mesh = 3,
		Custom = 4
	}

	public enum PhysicsEngine
	{
		Physics3D = 0,
		Physics2D = 1
	}

	public enum FlipMode
	{
		None = 0,
		Tk2d = 1,
		TPackerCW = 2
	}

	[Serializable]
	public class AttachPoint
	{
		public string name;

		public Vector3 position;

		public float angle;

		public void CopyFrom(AttachPoint src)
		{
		}

		public bool CompareTo(AttachPoint src)
		{
			return false;
		}
	}

	public string name;

	public Vector3[] boundsData;

	public Vector3[] untrimmedBoundsData;

	public Vector2 texelSize;

	public Vector3[] positions;

	public Vector3[] normals;

	public Vector4[] tangents;

	public Vector2[] uvs;

	public int[] indices;

	public Material material;

	[NonSerialized]
	public Material materialInst;

	public int materialId;

	public string sourceTextureGUID;

	public bool extractRegion;

	public int regionX;

	public int regionY;

	public int regionW;

	public int regionH;

	public FlipMode flipped;

	public bool complexGeometry;

	public PhysicsEngine physicsEngine;

	public ColliderType colliderType;

	public tk2dSpriteColliderDefinition[] customColliders;

	public Vector3[] colliderVertices;

	public int[] colliderIndicesFwd;

	public int[] colliderIndicesBack;

	public bool colliderConvex;

	public bool colliderSmoothSphereCollisions;

	public tk2dCollider2DData[] polygonCollider2D;

	public tk2dCollider2DData[] edgeCollider2D;

	public AttachPoint[] attachPoints;

	public bool Valid
	{
		get
		{
			return false;
		}
	}

	public Bounds GetBounds()
	{
		return default(Bounds);
	}

	public Bounds GetUntrimmedBounds()
	{
		return default(Bounds);
	}
}
