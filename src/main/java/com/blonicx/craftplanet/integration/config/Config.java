package com.blonicx.craftplanet.integration.config;

import com.blonicx.craftplanet.resources.ImagePipeline;

public class Config {
    // General
    public boolean filterChat = true;

    // Performance

    // General
    public boolean disableWeatherRendering = true;
    public ImagePipeline imagePipeline = ImagePipeline.DEFAULT;

    // Particle
    public int maxParticles = 5000;

    // Render Distance
    public int maxSignRenderDistance = 16;
    public int maxEntityRenderDistance = 64;
    public int maxBlockEntityRenderDistance = 64;
}
