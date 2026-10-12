using System;
using UnityEngine;

[Serializable]
public class prefabGenerator : MonoBehaviour
{
	public GameObject[] createThis;

	private float rndNr;

	public int thisManyTimes;

	public float overThisTime;

	public float xWidth;

	public float yWidth;

	public float zWidth;

	public float xRotMax;

	public float yRotMax;

	public float zRotMax;

	public bool allUseSameRotation;

	private bool allRotationDecided;

	public bool detachToWorld;

	private float x_cur;

	private float y_cur;

	private float z_cur;

	private float xRotCur;

	private float yRotCur;

	private float zRotCur;

	private float timeCounter;

	private int effectCounter;

	private float trigger;

	public virtual void Start()
	{
	}

	public virtual void Update()
	{
	}

	public virtual void Main()
	{
	}
}
