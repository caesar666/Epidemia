package graphics;

public class Shadow
{
	private float alphaX;
	private float alphaY;

	public float getAlpha()
	{
		return (alphaX + alphaY)/2;
	}

	public float getAlphaX()
	{
		return alphaX;
	}

	public void setAlphaX(float alphaX)
	{
		this.alphaX = alphaX;
	}

	public float getAlphaY()
	{
		return alphaY;
	}

	public void setAlphaY(float alphaY)
	{
		this.alphaY = alphaY;
	}

	public Shadow(float alphaX, float alphaY)
	{
		super();
		this.alphaX = alphaX;
		this.alphaY = alphaY;
	}

}
